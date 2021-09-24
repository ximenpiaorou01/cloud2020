package com.block.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.block.springcloud.alibaba.service.PaymentService;
import com.block.springcloud.entities.CommonResult;
import com.block.springcloud.entities.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class CircuitBreakController {

    @Value("${service-url.nacos-user-service}")
    private String serviceUrl;

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private PaymentService paymentService;

    /**
     * fallback是业务异常处理，blockHandler是对sentinnel控制台配置规则管理
     * @param id
     * @return
     */

    @GetMapping(value="/consumer/fallback/{id}")
    @SentinelResource(value="fallback",fallback="handlerFallback",blockHandler="blockHandler")
    public CommonResult<Payment> fallback(@PathVariable("id")Long id){
        CommonResult result = restTemplate.getForObject(serviceUrl + "/paymentSQL/" + id, CommonResult.class);
        if(id==4){
            throw new IllegalArgumentException("IllegalArgumentException,非法参数异常!");
        }else if(result.getData()==null){
            throw new IllegalArgumentException("NullPointerException,该id没有记录，空指针异常！");
        }
        return result;
    }


    public CommonResult handlerFallback(@PathVariable("id")Long id,Throwable e){
        Payment payment = new Payment(id, "null");
        return new CommonResult(444,"兜底异常handlerFallback,Exception内容:"+e.getMessage(),payment);
    }


    public CommonResult blockHandler(@PathVariable("id")Long id, BlockException e){
        Payment payment = new Payment(id, "null");
        return new CommonResult(445,"blockHandler-sentinel限流，无此流水。"+e.getMessage(),payment);
    }


    @GetMapping("/paymentSQL/{id}")
    public CommonResult paymentSQL(@PathVariable("id")Long id){
        return paymentService.paymentSQL(id);
    }


}
