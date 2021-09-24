package com.block.springcloud.alibaba.service;

import com.block.springcloud.entities.CommonResult;
import com.block.springcloud.entities.Payment;
import org.springframework.stereotype.Component;

@Component
public  class PaymentFallbackServiceImpl implements PaymentService {

    @Override
    public CommonResult<Payment> paymentSQL(Long id) {
        return new CommonResult<>(444,"服务降级，返回。",new Payment(id,"errorSerial"));
    }
}
