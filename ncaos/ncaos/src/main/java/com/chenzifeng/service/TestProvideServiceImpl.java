package com.chenzifeng.service;

import org.springframework.stereotype.Service;

/**
 * @Author: czf
 * @Description:
 * @Date: 2021-07-13 13:44
 * @Version: 1.0
 **/

@Service
public class TestProvideServiceImpl implements TestProvideService{
    @Override
    public String test1() {
        return "service provider : hello world!";
    }
}
