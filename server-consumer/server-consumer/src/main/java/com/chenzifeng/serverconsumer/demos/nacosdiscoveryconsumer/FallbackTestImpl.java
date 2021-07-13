package com.chenzifeng.serverconsumer.demos.nacosdiscoveryconsumer;

import org.springframework.stereotype.Service;

/**
 * @Author: czf
 * @Description:
 * @Date: 2021-07-13 17:18
 * @Version: 1.0
 **/

public class FallbackTestImpl implements TestProvideService{
    @Override
    public String test1() {
        return "服务暂时不可用，降级返回";

    }
}
