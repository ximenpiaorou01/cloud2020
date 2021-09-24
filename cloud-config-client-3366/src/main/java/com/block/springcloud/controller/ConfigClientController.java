package com.block.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class ConfigClientController {

    @Value("${server.port}")
    private String serverPort;

    @Value("${spring.application.name}")
    private String name;

    @GetMapping(value="/configInfo")
    public String configInfo(){
        return "serverPort:"+serverPort+"\t\n\n applicationName:"+name;
    }
}
