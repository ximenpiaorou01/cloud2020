package com.block.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {
    public String paymentInfo_OK(Integer id){
        return "线程池："+Thread.currentThread().getName()+" paymentInfo_OK,id:"+id+"\t"+"O(∩_∩)O哈哈~";
    }

    /*
     * 表示如果执行时超过配置3s，断路器会去执行paymentInfo_TimeOutHandler()丢底方法，如果未超过3s，就执行正常逻辑，需要在主启动类启动
     * 注解@EnableCircuitBreaker
     */
    @HystrixCommand(fallbackMethod="paymentInfo_TimeOutHandler",commandProperties = {
        @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")
    })
    public String paymentInfo_TimeOut(Integer id){
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池："+Thread.currentThread().getName()+" paymentInfo_OK,id:"+id+"\t"+"O(∩_∩)O哈哈~，耗时：5s";
    }


    public String paymentInfo_TimeOutHandler(Integer id){
        return "线程池："+Thread.currentThread().getName()+",id:"+id+" 系统繁忙，请稍后再试！,"+"o(╥﹏╥)o";
    }

    /**
     * 服务熔断，一下参数在HystrixCommandProperties类中
     * @param id
     * @return
     */
    @GetMapping(value="/payment/circuit/{id}")
    @HystrixCommand(fallbackMethod="paymentCircuitBreaker_fallback",commandProperties={
            @HystrixProperty(name="circuitBreaker.enabled",value="true"),//是否开启熔断
            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value="10"),//请求次数
            @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value="10000"),//时间窗口期
            @HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value="60")//错误达到多少跳闸
    })
    public String paymentCircuitBreaker(@PathVariable("id")Integer id){
        if(id<0){
            throw new RuntimeException("*******id 不能为负数");
        }
        String serialNumber= IdUtil.simpleUUID();
        return Thread.currentThread().getName()+"\t 调用成功,流水号:"+serialNumber;
    }

    public String paymentCircuitBreaker_fallback(@PathVariable("id")Integer id){
        return "id 不能为负数，请稍后再试,o(╥﹏╥)o id:"+id;
    }

}
