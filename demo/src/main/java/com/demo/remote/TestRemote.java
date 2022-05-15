package com.demo.remote;

import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @description:
 * @date: 2022/4/23 17:36
 * @author: LiHaoHan
 * @program: com.demo.remote
 */
@FeignClient(name = "test")
public interface TestRemote {

    // @GetMapping("test1")
    // String test1();

    @RequestLine("GET test2")
    String test2();
}
