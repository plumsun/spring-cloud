package com.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description:
 * @Date: 2021/10/19 18:00
 * @Author: LiHaoHan
 * @Program: com.demo.controller
 */
@MapperScan(basePackages = {"com.demo.*"})
// @EnableDiscoveryClient
@SpringBootApplication
//${prefix}-${spring.profiles.active}.${file-extension}
// @NacosPropertySource(dataId = "springboot_nacos-dev.1.0", autoRefreshed = true)
public class SpringBootDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoApplication.class, args);
    }

}
