package com.itheima.user.controller;

import com.itheima.user.pojo.User;
import com.itheima.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;

/**
 * @作者 itcast
 * @创建日期 2021/3/21 10:39
 **/
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
//        try {
//            Thread.sleep(i);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        User user = userService.queryByUsername(username);
        user.setEmail("提供服务端口==>"+port);
        return user;
    }
}
