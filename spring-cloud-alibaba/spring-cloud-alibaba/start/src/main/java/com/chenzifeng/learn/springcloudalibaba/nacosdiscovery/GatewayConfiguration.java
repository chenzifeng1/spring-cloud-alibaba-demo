package com.chenzifeng.learn.springcloudalibaba.nacosdiscovery;

import lombok.extern.slf4j.Slf4j;


import java.util.List;

/**
 * @Author: czf
 * @Description:
 * @Date: 2021-04-17 10:33
 * @Version: 1.0
 **/
@Slf4j

public class GatewayConfiguration {

//    private final List<ViewResolver> viewResolvers;
//    private final ServerCodecConfigurer serverCodecConfigurer;
//
//    public GatewayConfiguration(List<ViewResolver> viewResolvers, ServerCodecConfigurer serverCodecConfigurer) {
//        this.viewResolvers = viewResolvers;
//        this.serverCodecConfigurer = serverCodecConfigurer;
//    }
//
//    /**
//     * 配置 限流后异常处理  JsonSentinelGatewayBlockExceptionHandler重写 SentinelGatewayBlockExceptionHandler
//     * @return
//     */
//    @Bean
//    @Order(Ordered.HIGHEST_PRECEDENCE)
//    public JsonSentinelGatewayBlockExceptionHandler sentinelGatewayBlockExceptionHandler() {
//        // Register the block exception handler for Spring Cloud Gateway.
//        return new JsonSentinelGatewayBlockExceptionHandler(viewResolvers, serverCodecConfigurer);
//    }

//    /**
//     * 配置SentinelGatewayFilter
//     * @return
//     */
//    @Bean
//    @Order(Ordered.HIGHEST_PRECEDENCE)
//    public GlobalFilter sentinelGatewayFilter() {
//        return new SentinelGatewayFilter();
//    }

}
