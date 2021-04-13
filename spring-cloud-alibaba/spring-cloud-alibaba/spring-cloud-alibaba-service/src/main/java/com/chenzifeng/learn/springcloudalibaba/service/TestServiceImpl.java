package com.chenzifeng.learn.springcloudalibaba.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.chenzifeng.learn.springcloudalibaba.api.StaticContract;
import com.chenzifeng.learn.springcloudalibaba.api.TestService;
import org.springframework.stereotype.Service;

/**
 * @Author: czf
 * @Description:
 * @Date: 2021-04-13 18:55
 * @Version: 1.0
 **/
@Service
public class TestServiceImpl implements TestService {


    @Override
    @SentinelResource(value = "HelloWorld",blockHandler = "getBodyBack")
    public String getBody() {
        //业务逻辑
        //这里是被保护的资源
        return "hello_world";
    }

    /**
     * 降级方法
     * @return
     */
    public String getBodyBack(BlockException e){
        return "blockHandler";
    }

}
