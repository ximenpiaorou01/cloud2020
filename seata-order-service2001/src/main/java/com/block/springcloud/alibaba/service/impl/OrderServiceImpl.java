package com.block.springcloud.alibaba.service.impl;

import com.block.springcloud.alibaba.service.AccountService;
import com.block.springcloud.alibaba.dao.OrderMapper;
import com.block.springcloud.alibaba.service.StorageService;
import com.block.springcloud.alibaba.domain.Order;
import com.block.springcloud.alibaba.service.OrderService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;
    @Resource
    private AccountService accountService;
    @Resource
    private StorageService storageService;

    @Override
    //fsp_create_order是自定义，要唯一，rollbackFor表示哪些异常需要回滚，本次是所有异常要回滚
    @GlobalTransactional(name="fsp_create_order",rollbackFor=Exception.class)
    public void create(Order order) {

        log.info("-------start create order");
        orderMapper.create(order);

        log.info("-------订单微服务开始调用库存，开始扣减start");
        storageService.decrease(order.getProductId(),order.getCount());
        log.info("-------订单微服务开始调用库存，开始扣减end");

        log.info("-------订单微服务开始调用账户，开始做账户余额扣减start");
        accountService.decrease(order.getUserId(),order.getMoney());
        log.info("-------订单微服务开始调用账户，end");

        //修改订单状态完成
        log.info("-------修改订单状态开始");
        orderMapper.update(order.getUserId(),0);
        log.info("-------修改订单状态接受");
    }
}
