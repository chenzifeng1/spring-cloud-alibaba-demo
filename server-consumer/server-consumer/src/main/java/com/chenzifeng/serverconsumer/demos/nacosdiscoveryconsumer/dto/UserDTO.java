package com.chenzifeng.serverconsumer.demos.nacosdiscoveryconsumer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @Author: czf
 * @Description:
 * @Date: 2021-07-19 11:16
 * @Version: 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class UserDTO {

    private Long id;
    private String username;
    private String password;
    private Integer status;
    private List<String> roles;

    public UserDTO() {
    }
}
