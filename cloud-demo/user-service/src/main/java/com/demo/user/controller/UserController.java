package com.demo.user.controller;

import com.demo.user.pojo.User;
import com.demo.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("user")
@Slf4j
public class UserController {
    @Autowired
    UserService userService;
    @Value("${server.port}")
    int port;
    @GetMapping("{username}")
    public User queryByUsername(@PathVariable String username, HttpServletRequest request){
        System.out.println(request.getHeader("name"));
        int i = 5000;//new Random().nextInt(5000);
        log.info("服务被调用==> {}   睡眠时间==> {}",port,i);
        User user = userService.queryByUsername(username);
        user.setEmail("提供服务端口==>"+port);
        return user;
    }
}
