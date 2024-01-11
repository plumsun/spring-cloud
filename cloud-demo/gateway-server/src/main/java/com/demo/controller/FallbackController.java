package com.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * The type Fallback controller.
 *
 * @author LiHaoHan Created on 2024-01-11
 */
@RestController
public class FallbackController {

    /**
     * Fall back controller map.
     *
     * @return the map
     */
    @RequestMapping(value = "/fallbackTest")
    public Map<String, String> fallBackController() {
        Map<String, String> response = new HashMap<>();
        response.put("code", "502");
        response.put("msg", "服务超时");
        return response;
    }
}