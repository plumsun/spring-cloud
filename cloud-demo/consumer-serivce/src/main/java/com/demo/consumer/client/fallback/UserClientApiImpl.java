//package com.demo.consumer.client.fallback;
//
//import com.demo.consumer.client.UserClientApi;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Slf4j
//@Component
//public class UserClientApiImpl implements UserClientApi {
//    @Override
//    public Map queryByUsername(String username) {
//        log.info("feign ==> 服务降级方法被触发 username ==> {}",username);
//        Map map = new HashMap();
//        map.put("code","500");
//        map.put("msg","feign ==> 服务器出现异常了,请稍后重试");
//        return map;
//    }
//}
