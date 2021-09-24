package com.block.springcloud.alibaba.service.impl;

import com.block.springcloud.alibaba.dao.AccountMapper;
import com.block.springcloud.alibaba.service.AccountService;
import com.block.springcloud.entities.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.nio.channels.Channel;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountMapper accountMapper;

    @Override
    public CommonResult decrease(Long userId, BigDecimal money) {
        log.info("账户余额扣除开始...");

        //模拟超时异常，全局事务回滚
//        try {
//            TimeUnit.SECONDS.sleep(20);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        accountMapper.decrease(userId,money);
        log.info("账户余额扣除结束...");
        return new CommonResult(200,"账户余额扣除成功！");
    }
}
