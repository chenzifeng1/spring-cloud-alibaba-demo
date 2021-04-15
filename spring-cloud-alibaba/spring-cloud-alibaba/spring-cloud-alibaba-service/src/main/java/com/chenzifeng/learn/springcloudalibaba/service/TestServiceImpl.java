package com.chenzifeng.learn.springcloudalibaba.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.chenzifeng.learn.springcloudalibaba.api.StaticContract;
import com.chenzifeng.learn.springcloudalibaba.api.TestService;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

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

    @Override
    @SentinelResource(value = "ThreadNum",blockHandler = "threadNumBlockHandler")
    public String limitByThreadNum()  {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " 请求成功，执行业务逻辑");
        return "请求成功，执行业务逻辑";
    }

    @Override
    public String degradeTest()  {
       throw new RuntimeException("降级异常测试");
    }

    /**
     *  QPS降级方法
     * @return
     */
    public String getBodyBack(BlockException e){
        return "blockHandler";
    }

    /**
     * 线程数降级
     * @param e
     * @return
     */
    public String threadNumBlockHandler(BlockException e){
        System.out.println(Thread.currentThread().getName()+" 线程数不足，执行限流方法");
        return "线程数限流方法";
    }


}
