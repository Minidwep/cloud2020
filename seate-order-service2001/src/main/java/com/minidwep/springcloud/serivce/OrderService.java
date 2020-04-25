package com.minidwep.springcloud.serivce;

import com.minidwep.springcloud.domain.Order;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author zzyy
 * @date 2020/3/8 13:55
 **/

public interface OrderService {
    /**
     * 创建订单
     * @param order
     */
    void create(Order order);

    Long getIDBySnowFlake();
}