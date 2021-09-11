package com.block.rule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * warning:Ribbo的IRule实例不能放在@ComponentScan所在包及子包下，
 * 也就是不能放在启动各类所在包及子包下
 */
@Configuration
public class MyRule {

    @Bean
    public IRule getIRule(){
        return new RandomRule();
    }
}
