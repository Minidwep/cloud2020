package com.minidwep.springcloud.controller;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.minidwep.springcloud.entities.CommonResult;
import com.minidwep.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderNacosController {

    @Resource
    private RestTemplate restTemplate;

    @Value("${server-url.nacos-user-service}")
    private String serverURL;


    @GetMapping("/consumer/fallback/{id}")
    @SentinelResource(value = "fallback" , blockHandler = "blockHandler", fallback = "handleFallback")
    public CommonResult<Payment> paymentInfo(@PathVariable("id") Long id) {
        CommonResult<Payment> result = restTemplate.getForObject(serverURL + "/paymentSQL/" + id, CommonResult.class);
        if (id == 4) {
            throw new IllegalArgumentException("非法参数异常");
        } else if (result.getData() == null){
            throw new NullPointerException("null 空指针");
        }
        return result;
    }

    public CommonResult blockHandler(@PathVariable Long id, BlockException e){
        Payment payment = new Payment(id,"null");
        return new CommonResult(445,"blockHandler",e.getClass().getCanonicalName());
    }

    public CommonResult handleFallback(@PathVariable Long id,Throwable e){
        Payment payment = new Payment(id,"null");
        return new CommonResult(444,"兜底异常",payment);
    }
}