package com.minidwep.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class ConfigController {

    @Value("${config.info}")
    public String configInfo ;

    @Value("${server.port}")
    public String serverPort ;

    @GetMapping("/configInfo")
    public String getConfigInfo(){
        return "serverPort: "+serverPort+"configInfo "+configInfo;
    }
}
