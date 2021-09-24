package com.block.springcloud.alibaba.service;

import com.block.springcloud.entities.CommonResult;

public interface StorageService {
    CommonResult decrease(Long productId,Integer count);
}
