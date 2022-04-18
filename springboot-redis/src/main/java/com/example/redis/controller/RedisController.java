package com.example.redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisController {
    @Autowired
    private RedisTemplate redisTemplate;
    @GetMapping("/redis/get/{key}")
    public Object get(@PathVariable("key") String key){
       return redisTemplate.opsForValue().get(key);
    }
    @GetMapping("/redis/set")
    public String set(){
redisTemplate.opsForValue().set("str1","str1");
return "ok";
    }
}
