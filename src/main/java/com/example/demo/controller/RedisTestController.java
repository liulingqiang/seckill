package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liulq on 2018-11-22 .
 */
@RestController
@RequestMapping(value = "/redis")
public class RedisTestController {
    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping(value = "/setStr")
    void setStr(){
        redisTemplate.opsForValue().set("key", "Damein_xym");
        Object str = redisTemplate.opsForValue().get("key");
        System.out.println("===============" + str);
    }
}
