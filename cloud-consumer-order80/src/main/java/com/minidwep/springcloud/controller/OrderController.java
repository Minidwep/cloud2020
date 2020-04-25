package com.minidwep.springcloud.controller;

import com.minidwep.springcloud.entities.CommonResult;
import com.minidwep.springcloud.entities.Payment;
import com.minidwep.springcloud.lb.LoadBalance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

@RestController
@Slf4j
public class OrderController {
//    public static final String PAYMENT_URL ="http://localhost:8001";
    public static final String PAYMENT_URL ="http://CLOUD-PAYMENT-SERVICE";

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private LoadBalance loadBalance;

    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment){
        log.info("启动consumer create");
        return restTemplate.postForObject
                (PAYMENT_URL+"/payment/create", payment,CommonResult.class);
    }



    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id){
        log.info("启动consumer get");
        return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id,CommonResult.class);

    }


    @GetMapping("/consumer/payment/createForEntity")
    public CommonResult<Payment> create2(Payment payment){
        log.info("启动consumer create");
        return restTemplate.postForEntity
                (PAYMENT_URL+"/payment/create", payment,CommonResult.class).getBody();
    }


    @GetMapping("/consumer/payment/getForEntity/{id}")
    public CommonResult<Payment> getPayment2(@PathVariable("id") Long id){
        log.info("启动consumer get");
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
        if(entity.getStatusCode().is2xxSuccessful()){
            log.info(entity.getStatusCode()+"\t"+entity.getHeaders());
            return entity.getBody();
        }else {
            return new CommonResult<>(444,"操作失败");
        }
    }


    @GetMapping("/consumer/payment/lb")
    public String getPaymentLB(){
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if(instances ==null || instances.size() <= 0){
            return  null;
        }
        ServiceInstance Serviceinstance = loadBalance.instance(instances);
        URI uri = Serviceinstance.getUri();
        return restTemplate.getForObject(uri+"/payment/lb", String.class);
    }
    @GetMapping("/consumer/payment/zipkin")
    public String paymentZipKin(){
        String result = restTemplate.getForObject("http://localhost:8001"+"/payment/zipkin", String.class);
        return result;
    }

}
