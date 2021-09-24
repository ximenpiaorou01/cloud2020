package com.block.springcloud.alibaba.controller;

import com.block.springcloud.entities.CommonResult;
import com.block.springcloud.entities.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class PaymentController {
    @Value("${server.port}")
    private String serverPort;


    public static HashMap<Long, Payment> hashMap=new HashMap<>();
    static{
        hashMap.put(1l,new Payment(1l,"aaadwa21424214124asd"));
        hashMap.put(2l,new Payment(2l,"bbdwadwa424214124asd"));
        hashMap.put(3l,new Payment(3l,"cc11ad2a1211214124asd"));
    }

    @GetMapping(value="/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id")Long id){
        Payment payment = hashMap.get(id);
        CommonResult<Payment> commonResult = new CommonResult<>(200, "from mysql,serverPort:" + serverPort, payment);
        return commonResult;
    }
}
