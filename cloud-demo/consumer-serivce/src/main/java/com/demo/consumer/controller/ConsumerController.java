package com.demo.consumer.controller;

import lombok.extern.slf4j.Slf4j;
import org.feign.client.UserClientApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


/**
 * The type Consumer controller.
 *
 * @author LiHaoHan Created on 2024-01-11
 */
@RestController
@RequestMapping("consumer")
@Slf4j
public class ConsumerController {
    /**
     * The User client api.
     */
    @Autowired
    UserClientApi userClientApi;

    /**
     * Query by id map.
     *
     * @param username the username
     * @param request  the request
     * @return the map
     */
    @GetMapping("{username}")
    public Map queryById(@PathVariable String username, HttpServletRequest request){
        System.out.println(request.getHeader("name"));
        return userClientApi.queryByUsername(username);
    }



//    @Autowired
//    RestTemplate restTemplate;

//    @HystrixCommand(fallbackMethod = "queryByIdFallback")
//    @GetMapping("{username}")
//    public Map queryById(@PathVariable String username){
//        if("itheima".equals(username)){
//            throw new RuntimeException("太忙了");
//        }
//        // GET http://localhost:8081/user/itcast
//        //        ip:port部分  直接写 服务名称
//        return restTemplate.getForObject("http://user/user/"+username,Map.class);
//    }
//    public Map queryByIdFallback(String username){
//        log.info("服务降级方法被触发 username ==> {}",username);
//        Map map = new HashMap();
//        map.put("code","500");
//        map.put("msg","服务器出现异常了,请稍后重试");
//        return map;
//    }

//    @GetMapping("{username}")
//    public Map queryById(@PathVariable String username){
//        // GET http://localhost:8081/user/itcast
//        List<ServiceInstance> userList = discoveryClient.getInstances("user");
//        if(userList!= null && !userList.isEmpty()){
//            // 得到服务列表中第一个服务的信息
//            ServiceInstance serviceInstance = userList.get(0);
//            String host = serviceInstance.getHost();
//            int port = serviceInstance.getPort();
//            // 使用get请求获取用户数据
//            System.out.println("host的值:"+host);
//            System.out.println("port的值:"+port);
//            return restTemplate.getForObject("http://"+host+":"+port+"/user/"+username,Map.class);
//        }
//        return null;
//    }
}
