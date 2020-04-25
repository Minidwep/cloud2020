package com.minidwep.springcloud.service;

import com.minidwep.springcloud.dao.PaymentDao;
import com.minidwep.springcloud.entities.Payment;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class PaymentServiceImpl implements PaymentService{
    @Resource
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
