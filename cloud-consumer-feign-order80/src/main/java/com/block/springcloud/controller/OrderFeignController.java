package com.block.springcloud.controller;

import com.block.springcloud.entities.CommonResult;
import com.block.springcloud.entities.Payment;
import com.block.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderFeignController {

    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping(value="/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayentById(@PathVariable("id") Long id){
        return paymentFeignService.getPayentById(id);
    }

}
