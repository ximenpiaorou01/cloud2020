package com.block.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlowLimitController {

    @GetMapping(value="/testA")
    public String testA(){
        return  "--------testA";
    }

    @GetMapping(value="/testB")
    public String testB(){
        return  "--------testB";
    }


    @GetMapping(value="/testHotKey")
    @SentinelResource(value="testHotKey",blockHandler="deal_testHotKey")
    public String testC(@RequestParam(value="p1",required=false)String p1){
        return "testC is ok,😁";
    }

    public String deal_testHotKey(String p1, BlockException blockException){
        return "testC is failed,o(╥﹏╥)o";
    }

}
