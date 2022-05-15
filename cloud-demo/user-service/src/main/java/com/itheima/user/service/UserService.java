package com.itheima.user.service;

import com.itheima.user.mapper.UserMapper;
import com.itheima.user.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @作者 itcast
 * @创建日期 2021/3/21 10:38
 **/
@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    @Autowired
    RestTemplate restTemplate;
    public User queryByUsername(String username){



        return userMapper.queryByUsername(username);




    }


}
