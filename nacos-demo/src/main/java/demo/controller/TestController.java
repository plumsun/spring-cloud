package demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @date: 2022/4/23 17:33
 * @author: LiHaoHan
 * @program: com.demo.controller
 */
@RestController
@RequestMapping("/")
public class TestController {


    @GetMapping("test1")
    public String test1(){
        return "1";
    }

    @GetMapping("test2")
    public String test2(){
        return "2";
    }
}
