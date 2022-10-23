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
@SpringBootApplication
//includeFilters指定条件
//TODO @ComponentScan(value = "com.demo.controller",
//         includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Component.class})},
//         useDefaultFilters = false)
@EnableFeignClients(basePackages = "com.demo.remote")
// @EnableDiscoveryClient
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}


