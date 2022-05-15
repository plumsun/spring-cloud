package com.demo.config;

import feign.Contract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @date: 2022/4/28 13:12
 * @author: LiHaoHan
 * @program: com.demo.config
 */
@Configuration
public class FeignConfig {
    @Bean
    public Contract feignContract(){
        return new feign.Contract.Default();
    }
}
