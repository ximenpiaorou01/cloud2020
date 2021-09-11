package com.block.springcloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * feign 可以可以开启日志记录调用接口的相关信息
 */
@Configuration
public class FeignConfig {

    /**
     * 默认是NONE，还有BASIC，HEADER，FULL
     * @return
     */
    @Bean
    Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }
}
