package com.block.springcloud.alibaba.service;

import com.block.springcloud.entities.CommonResult;

import java.math.BigDecimal;

public interface AccountService {
    CommonResult decrease(Long userId, BigDecimal money);
}
