package com.chenzifeng.ncaos.service;

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
        int a = 1/ 0;
        return "service provider-1 : hello world!";
    }
}
