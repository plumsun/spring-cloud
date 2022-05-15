package com.demo.service;

import org.springframework.stereotype.Service;

/**
 * @description:
 * @date: 2022/4/23 17:35
 * @author: LiHaoHan
 * @program: com.demo.service
 */
@Service
public interface TestService {
    String feignTest1();

    String feignTest2();
}
