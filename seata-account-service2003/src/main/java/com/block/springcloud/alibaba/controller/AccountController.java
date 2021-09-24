package com.block.springcloud.alibaba.controller;

import com.block.springcloud.alibaba.service.AccountService;
import com.block.springcloud.entities.CommonResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;

@RestController
public class AccountController {

    @Resource
    private AccountService accountService;

    @PostMapping(value="/account/decrease")
    public CommonResult decrease(@RequestParam("userId")Long userId,
                                 @RequestParam("money")BigDecimal money){
        return accountService.decrease(userId,money);
    }

}
