package com.demo.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

//@SpringBootApplication
//@EnableDiscoveryClient
//@EnableCircuitBreaker // 开启熔断功能
@SpringCloudApplication // 整合上面3种注解
//@EnableFeignClients // 开启feign客户端扫描  当前包下所有的@FeignClient注解
public class ConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }
    @Bean
    @LoadBalanced // 开启负载均衡
    RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
