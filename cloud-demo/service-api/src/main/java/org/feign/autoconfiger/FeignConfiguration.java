package org.feign.autoconfiger;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
// 扫描FeignClient注解
@EnableFeignClients(basePackages = "org.feign")
// 扫描Spring注解
@ComponentScan(basePackages = "org.feign")
public class FeignConfiguration {
}
