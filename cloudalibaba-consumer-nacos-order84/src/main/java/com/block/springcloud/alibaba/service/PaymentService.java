package com.block.springcloud.alibaba.service;

import com.block.springcloud.entities.CommonResult;
import com.block.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value="nacos-payment-provider",fallback=PaymentFallbackServiceImpl.class)
public interface PaymentService {

    @GetMapping(value="/paymentSQL/{id}")
    CommonResult<Payment> paymentSQL(@PathVariable("id")Long id);
}
