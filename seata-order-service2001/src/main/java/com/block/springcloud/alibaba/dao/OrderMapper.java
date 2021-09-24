package com.block.springcloud.alibaba.dao;

import com.block.springcloud.alibaba.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderMapper {
    void create(Order order);

    void update(@Param("userId")Long userId,@Param("status")Integer status);
}
