package com.chenzifeng.serverconsumer.demos.nacosdiscoveryconsumer.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.chenzifeng.serverconsumer.demos.nacosdiscoveryconsumer.service.OpenFeignService;
import com.chenzifeng.serverconsumer.demos.nacosdiscoveryconsumer.service.TestProvideService;
import com.chenzifeng.serverconsumer.demos.nacosdiscoveryconsumer.service.blocked.ConsumerBlockHandler;
import com.chenzifeng.serverconsumer.demos.nacosdiscoveryconsumer.service.fallback.FallBackHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: czf
 * @Description:
 * @Date: 2021-07-14 17:01
 * @Version: 1.0
 **/

@Service
public class OpenFeignServiceImpl  implements OpenFeignService {

    @Autowired
    TestProvideService testProvideService;

    @Override
    @SentinelResource(value = "openFeign",fallback = "fallbackMethod",blockHandler = "blockResponse")
    public String openFeignTest() {
        return testProvideService.test1();
    }

    public String fallbackMethod() {
        return "这是在方法层面的进行的fallback";
    }

    public String blockResponse(){return "blocked";}

    @SentinelResource(value = "openFeign1",fallback = "fallbackMethod",blockHandlerClass = ConsumerBlockHandler.class)
    @Override
    public String openFeignTest1() {
        int i = 1/0;
        return testProvideService.test1();
    }

}
