package com.chenzifeng.learn.springcloudalibaba.api;

public interface ConfigService {


    /**
     * 刷新配置
     */
    void refreshConfig();

    /**
     * 获取username
     */
    String getUsername();

    /**
     * 获取年龄
     */
    int getAge();
}
