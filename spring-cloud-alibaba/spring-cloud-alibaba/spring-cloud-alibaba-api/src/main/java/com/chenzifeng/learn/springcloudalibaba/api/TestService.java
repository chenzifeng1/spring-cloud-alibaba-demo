package com.chenzifeng.learn.springcloudalibaba.api;

/**
 * @Author: czf
 * @Description:
 * @Date: 2021-04-13 18:53
 * @Version: 1.0
 **/
public interface TestService {

    /**
     * 测试方法
     * @return
     */
    String getBody();


    /**
     * 测试方法 线程数 限流
     * @return
     */
    String limitByThreadNum();

    /**
     * 降级测试方法
     * @return
     */
    String degradeTest() ;

}
