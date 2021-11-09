package com.demo;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Description:
 * @Date: 2021/10/19 18:00
 * @Author: LiHaoHan
 * @Program: com.demo.controller
 */
@SpringBootApplication
@EnableDiscoveryClient
//${prefix}-${spring.profiles.active}.${file-extension}
@NacosPropertySource(dataId = "springboot_nacos-dev.1.0", autoRefreshed = true)
public class SpringBootDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoApplication.class, args);
    }

}
