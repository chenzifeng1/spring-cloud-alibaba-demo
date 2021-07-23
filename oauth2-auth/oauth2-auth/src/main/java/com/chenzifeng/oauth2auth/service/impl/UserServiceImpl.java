package com.chenzifeng.oauth2auth.service.impl;

import com.chenzifeng.oauth2auth.constant.MessageConstant;
import com.chenzifeng.oauth2auth.dto.SecurityUser;
import com.chenzifeng.oauth2auth.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: czf
 * @Description:
 * @Date: 2021-07-16 13:40
 * @Version: 1.0
 **/

@Service
public class UserServiceImpl implements UserDetailsService {

    private List<SecurityUser> userList;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostConstruct
    public void initData() {
        String password = passwordEncoder.encode("123456");
        userList = new ArrayList<>();
        userList.add(new SecurityUser("macro", password, AuthorityUtils.commaSeparatedStringToAuthorityList("ADMIN")));
        userList.add(new SecurityUser("andy", password, AuthorityUtils.commaSeparatedStringToAuthorityList("CLIENT")));
        userList.add(new SecurityUser("mark", password, AuthorityUtils.commaSeparatedStringToAuthorityList("CLIENT")));
    }


    /**
     *  这里实际上是对用户状态一个初步过滤 其实应该是从数据库查询 然后看一下用户状态和对用户进行一个密码
     * @param s
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        List<SecurityUser> collect = userList.stream().filter(item -> item.getUsername().equals(s)).collect(Collectors.toList());
        if(collect.isEmpty()){
            throw new UsernameNotFoundException("未找到用户信息");
        }
        SecurityUser securityUser = collect.get(0);
        if(!securityUser.getEnabled()){
            throw new DisabledException(MessageConstant.ACCOUNT_DISABLED);
        }
        if(!securityUser.isAccountNonLocked()){
            throw new LockedException(MessageConstant.ACCOUNT_LOCKED);
        }
        if(!securityUser.isAccountNonExpired()){
            throw new AccountExpiredException(MessageConstant.ACCOUNT_EXPIRED);
        }
        if(!securityUser.isCredentialsNonExpired()){
            throw new CredentialsExpiredException(MessageConstant.CREDENTIALS_EXPIRED);
        }

        return securityUser;
    }




}
