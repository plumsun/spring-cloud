package com.demo.redistest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Set;

@SpringBootTest
class RedisTestApplicationTests {


    @Autowired
    RedisTemplate redisTemplate;


    @Test
    void contextLoads() {
        Set keys = redisTemplate.keys("*");
        System.out.println("keys = " + keys);
    }

}
