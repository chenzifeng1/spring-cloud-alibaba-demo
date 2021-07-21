package com.chenzifeng.oauth2auth.service.impl;

import com.chenzifeng.oauth2auth.constant.RedisConstant;
import com.chenzifeng.oauth2auth.utils.CollUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @Author: czf
 * @Date: 2021-07-16 17:06
 * @Description: 初始化的时候把资源与角色匹配关系缓存到Redis中，方便网关服务进行鉴权的时候获取
 * @Version: 1.0
 **/
@Service
public class ResourceServiceImpl {
    private Map<String, List<String>> resourceRolesMap ;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @PostConstruct
    public void initData(){
        resourceRolesMap = new TreeMap<>();
        resourceRolesMap.put("/api/hello", CollUtil.toList("ADMIN"));
        resourceRolesMap.put("/api/user/currentUser", CollUtil.toList("ADMIN", "TEST"));
        redisTemplate.opsForHash().putAll(RedisConstant.RESOURCE_ROLES_MAP, resourceRolesMap);

    }



}
