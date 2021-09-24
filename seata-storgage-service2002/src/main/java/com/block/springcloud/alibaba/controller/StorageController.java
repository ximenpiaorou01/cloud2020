package com.block.springcloud.alibaba.controller;

import com.block.springcloud.alibaba.service.StorageService;
import com.block.springcloud.entities.CommonResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class StorageController {

    @Resource
    private StorageService storageService;

    @PostMapping(value="/storage/decrease")
    public CommonResult decrease(@RequestParam("productId")Long productId,@RequestParam("count")Integer count){
        return storageService.decrease(productId,count);
    }
}
