package com.block.springcloud.alibaba.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.block.springcloud.alibaba.dao")
public class MybatisConfig {
}
