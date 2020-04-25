package com.minidwep.springcloud.servicer;

import com.minidwep.springcloud.entities.CommonResult;
import com.minidwep.springcloud.entities.Payment;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {
    @GetMapping(value = "/payment/get/{id}")
    CommonResult getPaymentById(@PathVariable("id") Long id);
    @GetMapping("/payment/feign/timeout")
    String getPaymentFeignTimeout();

}
