package com.chenzifeng.learn.springcloudalibaba.service;

import com.chenzifeng.learn.springcloudalibaba.api.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: czf
 * @Description:
 * @Date: 2021-04-20 19:14
 * @Version: 1.0
 **/
@Service
public class ConfigServiceImpl implements ConfigService {

    private DataConfiguration dataConfiguration;


    @Override
    public void refreshConfig() {
        dataConfiguration.refreshConfig();
    }

    @Override
    public String getUsername() {
       return dataConfiguration.getUsername();
    }

    @Override
    public int getAge() {
       return dataConfiguration.getAge();
    }

    @Autowired
    public void setDataConfiguration(DataConfiguration dataConfiguration) {
        this.dataConfiguration = dataConfiguration;
    }
}
