package com.block.springcloud.alibaba.service.impl;

import com.block.springcloud.alibaba.dao.StorageMapper;
import com.block.springcloud.alibaba.service.StorageService;
import com.block.springcloud.entities.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class StorageServiceImpl implements StorageService {

    @Resource
    private StorageMapper storageMapper;
    @Override
    public CommonResult decrease(Long productId, Integer count) {
        log.info("调用库存开始...");
        storageMapper.decrease(productId,count);
        log.info("调用库存结束...");

        return new CommonResult(200,"调用库存成功");
    }
}
