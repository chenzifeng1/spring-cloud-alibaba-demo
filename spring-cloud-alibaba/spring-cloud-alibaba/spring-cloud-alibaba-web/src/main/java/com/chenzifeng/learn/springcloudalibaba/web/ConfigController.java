package com.chenzifeng.learn.springcloudalibaba.web;

import com.chenzifeng.learn.springcloudalibaba.api.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: czf
 * @Description:
 * @Date: 2021-04-20 19:11
 * @Version: 1.0
 **/
@RestController

public class ConfigController {
    private ConfigService configService;

    @GetMapping("/getConfig")
    public String getConfig() {
        configService.refreshConfig();
        return "user: " + configService.getUsername() + " age: " + configService.getAge();
    }

    @GetMapping("/getUsername")
    public String getUsername(){
         return configService.getUsernameFromAnnotation();
    }

    @Autowired
    public void setConfigService(ConfigService configService) {
        this.configService = configService;
    }
}
