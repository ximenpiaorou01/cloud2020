package com.block.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.block.springcloud.entities.CommonResult;
import com.block.springcloud.entities.Payment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RateLimitController {

    @GetMapping(value="/byResource")
    @SentinelResource(value="byResource",blockHandler="handleException")
    public CommonResult byResource(){
        return new CommonResult(200,"按资源名称限流测试ok",new Payment(200l,"serial001"));
    }

    public CommonResult handleException(BlockException exception){
        return new CommonResult(444,exception.getClass().getCanonicalName()+"\t 服务器不可用");
    }

    @GetMapping(value="/customerBlockHandler")
    @SentinelResource(value="customerBlockHandler",blockHandlerClass=CustomerBlockException.class,blockHandler="handlerException2")
    public CommonResult customerBlockHandler(){
        return new CommonResult(200,"按客户自定义限流测试ok",new Payment(200l,"serial001"));
    }

}
