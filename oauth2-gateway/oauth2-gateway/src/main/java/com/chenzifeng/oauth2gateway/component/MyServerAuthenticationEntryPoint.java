package com.chenzifeng.oauth2gateway.component;

import cn.hutool.json.JSONUtil;
import com.chenzifeng.oauth2gateway.api.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;


/**
 * @Author: czf
 * @Description:
 * @Date: 2021-08-03 10:37
 * @Version: 1.0
 **/
@Slf4j
public class MyServerAuthenticationEntryPoint implements ServerAuthenticationEntryPoint {


    @Override
    public Mono<Void> commence(ServerWebExchange serverWebExchange, AuthenticationException e) {

        if(e instanceof AccountExpiredException){
            // token 过期
            log.info("token失效");
            return serverWebExchange.getResponse().writeWith(Mono.just(tokenExpired(serverWebExchange,e.getMessage())));
        }else{
            log.info("解析token");

        }


        return null;
    }

    /**
     * 处理token过期请求
     * @param exchange
     * @return
     */
    private DataBuffer tokenExpired(ServerWebExchange exchange,String message){
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.OK);
        response.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        String body = JSONUtil.toJsonStr(CommonResult.unauthorized(message));
        return response.bufferFactory().wrap(body.getBytes(StandardCharsets.UTF_8));
    }

    /**
     *
     * @param serverWebExchange
     * @param message
     * @return
     */
    private DataBuffer successTokens(ServerWebExchange serverWebExchange,String message){
        ServerHttpResponse response = serverWebExchange.getResponse();
        response.setStatusCode(HttpStatus.OK);
        response.getHeaders().add(HttpHeaders.CONTENT_TYPE,MediaType.APPLICATION_JSON_VALUE);
        String body = JSONUtil.toJsonStr(CommonResult.success(message));
        return response.bufferFactory().wrap(body.getBytes(StandardCharsets.UTF_8));
    }

}
