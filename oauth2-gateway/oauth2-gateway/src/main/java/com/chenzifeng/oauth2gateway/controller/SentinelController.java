package com.chenzifeng.oauth2gateway.controller;

import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiDefinition;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.GatewayApiDefinitionManager;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * @Author: czf
 * @Description: 这里是做限流的测试
 * @Date: 2021-08-04 16:36
 * @Version: 1.0
 **/
@RestController
@Slf4j
public class SentinelController {

    @GetMapping("/api")
    @SentinelResource(value = "api",fallback = "apiFallbackUrl")
    public Set<ApiDefinition> apiRules(){
        log.info("api 访问 流量控制");
        return GatewayApiDefinitionManager.getApiDefinitions();
    }


    public String apiFallbackUrl(){
        return "访问量过多，请稍后再试";
    }
}
