package com.demo.service;

import com.demo.remote.TestRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @date: 2022/4/23 17:36
 * @author: LiHaoHan
 * @program: com.demo.service
 */
@Service
public class TestServiceImpl implements TestService{


    @Autowired
    TestRemote testRemote;

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public String feignTest1() {
        return redisTemplate.toString();
    }

    @Override
    public String feignTest2() {
        return testRemote.test2();
    }
}
