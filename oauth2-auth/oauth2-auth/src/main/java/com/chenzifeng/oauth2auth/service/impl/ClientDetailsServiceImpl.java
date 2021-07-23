package com.chenzifeng.oauth2auth.service.impl;

import com.chenzifeng.oauth2auth.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

/**
 * @Author: czf
 * @Description:
 * @Date: 2021-07-23 10:47
 * @Version: 1.0
 **/

@AllArgsConstructor
@Service
public class ClientDetailsServiceImpl implements ClientDetailsService {

    private  final ClientService clientService;


    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        return null;
    }
}
