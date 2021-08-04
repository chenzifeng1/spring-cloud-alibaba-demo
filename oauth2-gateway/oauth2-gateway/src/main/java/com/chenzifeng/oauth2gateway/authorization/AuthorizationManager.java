package com.chenzifeng.oauth2gateway.authorization;

import cn.hutool.core.convert.Convert;
import com.chenzifeng.oauth2gateway.constant.AuthConstant;
import com.chenzifeng.oauth2gateway.constant.RedisConstant;
import com.nimbusds.oauth2.sdk.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: czf
 * @Description:
 * @Date: 2021-07-19 9:58
 * @Version: 1.0
 **/
@Component
@Slf4j
public class AuthorizationManager implements ReactiveAuthorizationManager<AuthorizationContext> {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;


    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> mono, AuthorizationContext authorizationContext) {
        return doCheck(mono,authorizationContext);
//        //从Redis中获取当前路径可访问角色列表
//        URI uri = authorizationContext.getExchange().getRequest().getURI();
//        Object obj = redisTemplate.opsForHash().get(RedisConstant.RESOURCE_ROLES_MAP, uri.getPath());
//        List<String> authorities = Convert.toList(String.class,obj);
//        authorities = authorities.stream().map(i -> i = AuthConstant.AUTHORITY_PREFIX + i).collect(Collectors.toList());
//
//        //认证通过且角色匹配的用户可访问当前路径
//        return mono
//                .filter(Authentication::isAuthenticated)
//                .flatMapIterable(Authentication::getAuthorities)
//                .map(GrantedAuthority::getAuthority)
//                .any(authorities::contains)
//                .map(AuthorizationDecision::new)
//                .defaultIfEmpty(new AuthorizationDecision(false));
    }

    /**
     *
     * @param mono
     * @param authorizationContext
     * @return
     */
    private Mono<AuthorizationDecision> doCheck(Mono<Authentication> mono, AuthorizationContext authorizationContext){
        ServerWebExchange exchange = authorizationContext.getExchange();
        URI uri = exchange.getRequest().getURI();
        Object obj = redisTemplate.opsForHash().get(RedisConstant.RESOURCE_ROLES_MAP, uri.getPath());
        List<String> authorities = Convert.toList(String.class,obj);
        // 拿到redis缓存的接口权限关系
        authorities = authorities.stream().map(i -> i = AuthConstant.AUTHORITY_PREFIX + i).collect(Collectors.toList());
        // 拿到授权的token
        String authorizationToken = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        log.info("当前请求头Authorization中的值:{}",authorizationToken);
        if (StringUtils.isBlank(authorizationToken)) {
            log.warn("当前请求头Authorization中的值不存在");
            return Mono.just(new AuthorizationDecision(false));
        }
        PathMatcher pathMatcher = new AntPathMatcher();
        for (String authority : authorities) {
            if (pathMatcher.match(authority, uri.getPath())) {
                //设置请求头参数username
                ServerHttpRequest request = exchange.getRequest().mutate()
                        .headers(httpHeaders -> httpHeaders.add("username",authority)).build();
                exchange.mutate().request(request).build();
                return Mono.just(new AuthorizationDecision(true));
            }
        }
        return Mono.just(new AuthorizationDecision(true));


    }

}
