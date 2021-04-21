package com.chenzifeng.learn.springcloudalibaba.service;

import com.chenzifeng.learn.springcloudalibaba.api.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @Author: czf
 * @Description:
 * @Date: 2021-04-20 19:14
 * @Version: 1.0
 **/
@Service
@RefreshScope
public class ConfigServiceImpl implements ConfigService {

    private DataConfiguration dataConfiguration;


//    @NacosValue()
    private String annotationUsername;
    @Value("${mytest.str}")
    private String test;



    @Override
    public void refreshConfig() {
        dataConfiguration.refreshConfig();
    }

    @Override
    public String getUsername() {
       return dataConfiguration.getUsername();
    }

    @Override
    public String getUsernameFromAnnotation() {
        return annotationUsername;
    }

    @Override
    public int getAge() {
       return dataConfiguration.getAge();
    }

    @Autowired
    public void setDataConfiguration(DataConfiguration dataConfiguration) {
        this.dataConfiguration = dataConfiguration;
    }

    @PostConstruct
    public void init(){
        System.out.println("获取到的配置为： " + test);
    }
}
