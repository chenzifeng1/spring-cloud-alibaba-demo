package com.chenzifeng.oauth2auth.jwt;

import com.chenzifeng.oauth2auth.dto.SecurityUser;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * @Author: czf
 * @Description:
 * @Date: 2021-07-16 15:36
 * 向jwt的token中加入一些自定义的信息
 * @Version: 1.0
 **/
@Component
public class JwtTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        SecurityUser user = (SecurityUser) authentication.getPrincipal();
        final HashMap<String,Object> additionalInformation = new HashMap<>();
        // 注意添加的额外信息，最好不要和已有的json对象中的key重名，容易出现错误
        additionalInformation.put("custom_info",user.getUsername());
        ((DefaultOAuth2AccessToken)accessToken).setAdditionalInformation(additionalInformation);
        return accessToken;
    }
}
