package com.demo.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@MapperScan("com.demo.user.mapper")
//@EnableEurekaClient // 仅仅eureka 作为注册中心时 可以使用
@EnableDiscoveryClient  // 用什么注册中心 都可以使用的客户端注解
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}
