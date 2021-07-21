package com.chenzifeng.serverconsumer.demos.nacosdiscoveryconsumer.controller;

import com.chenzifeng.serverconsumer.demos.nacosdiscoveryconsumer.dto.UserDTO;
import com.chenzifeng.serverconsumer.demos.nacosdiscoveryconsumer.handler.LoginUserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: czf
 * @Description:
 * @Date: 2021-07-19 11:23
 * @Version: 1.0
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private LoginUserHolder loginUserHolder;

    @GetMapping("/currentUser")
    public UserDTO currentUser() {
        return loginUserHolder.getCurrentUser();
    }


}
