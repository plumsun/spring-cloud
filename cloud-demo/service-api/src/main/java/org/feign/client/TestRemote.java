package org.feign.client;

import org.feign.autoconfiger.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.HashMap;

/**
 * @description:
 * @date: 2022/4/7 14:08
 * @author: LiHaoHan
 * @program: org.feign
 */
@FeignClient(name = "bk", url = "127.0.0.1:8003",configuration = FeignConfiguration.class)
public interface TestRemote {

    @PostMapping("/excel/demo")
    void demo(@RequestHeader("qq") String qq, @RequestBody HashMap<String, Integer> map);
}
