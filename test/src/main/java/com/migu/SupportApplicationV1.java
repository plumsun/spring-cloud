package com.migu;

import com.migu.gvpcore.config.FeignClientConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableFeignClients(defaultConfiguration = FeignClientConfig.class)
@SpringBootApplication
public class SupportApplicationV1 {

    public static void main(String[] args) {

        SpringApplication.run(SupportApplicationV1.class,args);
    }

}
