package com.chenzifeng.serverconsumer.demos.nacosdiscoveryconsumer.handler;

import cn.hutool.core.convert.Convert;
import com.alibaba.fastjson.JSONObject;
import com.chenzifeng.serverconsumer.demos.nacosdiscoveryconsumer.dto.UserDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: czf
 * @Description:
 * @Date: 2021-07-19 11:15
 * @Version: 1.0
 **/

@Component
public class LoginUserHolder {

    public UserDTO getCurrentUser(){
        //从Header中获取用户信息
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String userStr = request.getHeader("user");
        JSONObject userJsonObject = JSONObject.parseObject(userStr);
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(userJsonObject.getString("user_name"));
        userDTO.setId(Convert.toLong(userJsonObject.get("id")));
        userDTO.setRoles(Convert.toList(String.class,userJsonObject.get("authorities")));
        return userDTO;
    }


}
