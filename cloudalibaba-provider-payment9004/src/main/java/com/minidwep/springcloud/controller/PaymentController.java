package com.minidwep.springcloud.controller;

import com.minidwep.springcloud.entities.CommonResult;
import com.minidwep.springcloud.entities.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;


    public static HashMap<Long, Payment> hashMap  = new HashMap<>();
    static
    {
        hashMap.put(1L, new Payment(1L,"123"));
        hashMap.put(2L, new Payment(2L,"234"));
        hashMap.put(3L, new Payment(3L,"345"));
        hashMap.put(4L, new Payment(4L,"456"));

    }

    @GetMapping("/paymentSQL/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id){
        Payment payment = hashMap.get(id);
        CommonResult<Payment> result = new CommonResult<>(200,"from mysql,serverport:"+serverPort,payment);
        return result;
    }
}