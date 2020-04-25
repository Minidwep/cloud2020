package com.minidwep.springcloud.controller;

import com.minidwep.springcloud.domain.CommonResult;
import com.minidwep.springcloud.domain.Order;
import com.minidwep.springcloud.serivce.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class OrderController {
    @Resource
    private OrderService orderService;


    @GetMapping("order/create")
    public CommonResult create(Order order) {
        orderService.create(order);
        return new CommonResult(200, "订单创建成功");
    }

    @GetMapping("/snowflake")
    public Long index() {
        return orderService.getIDBySnowFlake();
    }
}
