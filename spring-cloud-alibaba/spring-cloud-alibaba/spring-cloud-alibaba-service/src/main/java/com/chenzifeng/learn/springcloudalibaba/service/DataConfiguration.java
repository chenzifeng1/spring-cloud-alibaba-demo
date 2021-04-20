package com.chenzifeng.learn.springcloudalibaba.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Author: czf
 * @Description:
 * @Date: 2021-04-20 18:51
 * @Version: 1.0
 **/
@Component
@Slf4j
public class DataConfiguration implements ApplicationContextAware {

    private String username;
    private int age;
    private ApplicationContext applicationContext;

    public void refreshConfig() {
        this.username = applicationContext.getEnvironment().getProperty("user.name");
        this.age = Integer.parseInt(applicationContext.getEnvironment().getProperty("age"));
        log.info("获取配置：username: " + username + " age: " + age);
    }

    public String getUsername() {
        return username;
    }

    public int getAge() {
        return age;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
