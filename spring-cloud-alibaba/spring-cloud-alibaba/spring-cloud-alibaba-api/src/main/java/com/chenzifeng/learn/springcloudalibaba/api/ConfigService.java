package com.chenzifeng.learn.springcloudalibaba.api;

public interface ConfigService {


    /**
     * 刷新配置
     */
    void refreshConfig();

    /**
     * 获取username
     * @return username
     */
    String getUsername();

    /**
     * 获取username 使用@Valeu注解的方式
     * @return username
     */
    String getUsernameFromAnnotation();


    /**
     * 获取年龄
     */
    int getAge();
}
