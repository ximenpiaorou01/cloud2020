package com.block.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.block.springcloud.entities.CommonResult;

/**
 * 用户自定义异常处理类
 */
public class CustomerBlockException {
    public static CommonResult handlerException2(BlockException exception){
        return new CommonResult(4444,"用户自定义限流处理信息。。。。");
    }
}
