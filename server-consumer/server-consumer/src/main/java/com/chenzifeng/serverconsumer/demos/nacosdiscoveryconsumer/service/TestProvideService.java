package com.chenzifeng.serverconsumer.demos.nacosdiscoveryconsumer.service;

import com.chenzifeng.serverconsumer.demos.nacosdiscoveryconsumer.service.fallback.FallbackTestImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author: czf
 * @Description:
 * @Date: 2021-07-13 15:20
 * @Version: 1.0
 **/

@FeignClient(name="nacos",fallback = FallbackTestImpl.class)
public interface TestProvideService {

    /**
     * 测试代码
     * @return
     */
    @GetMapping("/test/t1")
    String test1();

}
