package com.chenzifeng.serverconsumer.demos.nacosdiscoveryconsumer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: czf
 * @Description:
 * @Date: 2021-07-13 17:26
 * @Version: 1.0
 **/
@Configuration
public class FeignConfiguration {
    @Bean
    public FallbackTestImpl fallbackTestImpl(){
        return new FallbackTestImpl();
    }
}
