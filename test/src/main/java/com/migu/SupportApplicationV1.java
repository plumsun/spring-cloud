package com.migu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableFeignClients
@SpringBootApplication
public class SupportApplicationV1 {

    public static void main(String[] args) {

        SpringApplication.run(SupportApplicationV1.class,args);
    }

}
