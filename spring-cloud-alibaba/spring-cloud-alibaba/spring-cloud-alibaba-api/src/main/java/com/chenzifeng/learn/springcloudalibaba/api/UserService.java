package com.chenzifeng.learn.springcloudalibaba.api;


import com.chenzifeng.learn.springcloudalibaba.api.model.UserModel;

/**
 * @author <a href="mailto:chenxilzx1@gmail.com">theonefx</a>
 */
public interface UserService {

    String getUserName(Long id);

    UserModel addUser(UserModel user);
}
