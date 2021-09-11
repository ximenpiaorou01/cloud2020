package com.block.springcloud.service;


import com.block.springcloud.entities.CommonResult;
import com.block.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//调用指定微服务名
@FeignClient(value="cloud-payment-service")
public interface PaymentFeignService {

    @GetMapping(value="/payment/get/{id}")
    CommonResult<Payment> getPayentById(@PathVariable("id") Long id);
}
