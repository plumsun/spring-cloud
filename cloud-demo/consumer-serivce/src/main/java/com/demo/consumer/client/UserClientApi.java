//package com.demo.consumer.client;
//
//
//import com.demo.consumer.client.fallback.UserClientApiImpl;
//import com.demo.consumer.config.FeignConfig;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//
//import java.util.Map;
//
//// 当前接口是feign客户端
//@FeignClient(value = "user",fallback = UserClientApiImpl.class,configuration = FeignConfig.class)
//public interface UserClientApi {
//    // JDK动态代理 基于这个接口给我们生成一个代理对象
//    // 代理对象被执行，会解析方法的配置
//    // GET http://user/user/{username} ==> GET http://user/user/itcast
//    //  Feign内部 内置了Ribbon    调用ribbon 执行 GET http://user/user/itcast
//    @GetMapping("/user/{username}")
//    Map queryByUsername(@PathVariable("username") String username);
//}
