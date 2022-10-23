package com.demo.controller;

import com.demo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @description:
 * @date: 2022/4/23 17:33
 * @author: LiHaoHan
 * @program: com.demo.controller
 */
@RestController
@RequestMapping("/")
public class TestController {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    private TestService testService;

    @Autowired
    Environment environment;

    @GetMapping("demo1")
    public String demo1(){
        return this.environment.getProperty("file");
    }

    @GetMapping("demo2")
    public String demo2(){
        return this.testService.feignTest2();
    }

    @GetMapping("str")
    public String str(@RequestParam String key,@RequestParam String value){
        stringRedisTemplate.opsForValue().set(key,value);
        return stringRedisTemplate.opsForValue().get(key);
    }

    @GetMapping("list")
    public String list(@RequestParam String key,@RequestBody ArrayList<String> list){
        for (String s : list) {
            stringRedisTemplate.opsForList().leftPush(key,s);
        }
        return stringRedisTemplate.opsForList().leftPop(key);
    }

    @GetMapping("set")
    public String set(@RequestParam String key,@RequestBody HashSet<String> set){
        for (String s : set) {
            stringRedisTemplate.opsForSet().add(key,s);
        }
        return stringRedisTemplate.opsForSet().members(key).toString();
    }

    //TODO 改变事务的传播行为
    @Transactional(propagation = Propagation.NOT_SUPPORTED,rollbackFor = Exception.class)
    @GetMapping("hash")
    public String hash(@RequestParam String key,@RequestParam String key3,@RequestBody HashMap<String,String> map){
        Set<Map.Entry<String, String>> entries = map.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            String key1 = entry.getKey();
            String value = entry.getValue();
            stringRedisTemplate.opsForHash().put(key,key1,value);
        }
        return stringRedisTemplate.opsForHash().get(key,key3).toString();
    }
}
