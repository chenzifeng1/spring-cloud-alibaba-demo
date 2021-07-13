package com.chenzifeng.ncaos.controller;


import com.chenzifeng.ncaos.service.TestProvideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: czf
 * @Description:
 * @Date: 2021-07-13 13:45
 * @Version: 1.0
 **/
@RestController
@RequestMapping("/test")
public class TestProvideController {

    @Autowired
    TestProvideService testService;


    @GetMapping("/t1")
    public String test1(){
        return testService.test1();
    }
}
