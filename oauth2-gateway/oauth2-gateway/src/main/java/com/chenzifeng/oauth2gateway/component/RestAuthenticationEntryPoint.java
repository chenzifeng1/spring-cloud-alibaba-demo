package com.chenzifeng.oauth2gateway.component;

import com.chenzifeng.oauth2gateway.api.CommonResult;
import cn.hutool.json.JSONUtil;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.Charset;

import static org.bouncycastle.asn1.x500.style.RFC4519Style.cn;

/**
 * @Author: czf
 * @Description:
 * 这里是对jwt的token未授权或者过期时，返回做的处理
 * @Date: 2021-07-19 10:17
 * @Version: 1.0
 **/
@Component
public class RestAuthenticationEntryPoint implements ServerAuthenticationEntryPoint  {
    @Override
    public Mono<Void> commence(ServerWebExchange exchange, AuthenticationException e) {
        // 获取响应信息
        ServerHttpResponse response = exchange.getResponse();
        // 虽然没有授权或者token已经过期了，但是也是一次成功处理的请求
        response.setStatusCode(HttpStatus.OK);
        response.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        // 这里把未授权的返回的信息放到body中
        String body= JSONUtil.toJsonStr(CommonResult.unauthorized(e.getMessage()));

        DataBuffer buffer = response.bufferFactory().wrap(body.getBytes(Charset.forName("UTF-8")));

        return response.writeWith(Mono.just(buffer));
    }
}