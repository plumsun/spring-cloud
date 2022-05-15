package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @description:
 * @date: 2022/4/23 17:25
 * @author: LiHaoHan
 * @program: com.demo
 */
@EnableFeignClients
// @EnableDiscoveryClient
@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
