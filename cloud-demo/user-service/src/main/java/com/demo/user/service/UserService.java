package com.demo.user.service;

import com.demo.user.mapper.UserMapper;
import com.demo.user.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    @Autowired
    RestTemplate restTemplate;

    public User queryByUsername(String username) {
        return userMapper.queryByUsername(username);
    }
}
