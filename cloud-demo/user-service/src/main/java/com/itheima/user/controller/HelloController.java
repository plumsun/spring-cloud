package com.itheima.user.controller;

import com.itheima.user.config.RedisProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 
 */
@RestController
@RequestMapping("hello")
public class HelloController {

    @Autowired
    private RedisProperties properties;

    @GetMapping("prop")
    public RedisProperties getProperties(){
        return properties;
    }
}