package com.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/config")
public class ConfigController {

    // @Value("${useLocalCache:false}")
    // private boolean useLocalCache;

    @GetMapping("/get")
    public String get() {
        return "2";
    }
}