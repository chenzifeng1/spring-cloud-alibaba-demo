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
    @SentinelResource(value = "openFeign",fallbackClass = FallBackHandler.class,blockHandlerClass = ConsumerBlockHandler.class)
    public String openFeignTest() {

        return testProvideService.test1();
    }
}
