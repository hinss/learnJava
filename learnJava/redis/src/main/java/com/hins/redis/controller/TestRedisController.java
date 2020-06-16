package com.hins.redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/redis")
public class TestRedisController {

    @Resource(name = "myRedisTemplate")
    private RedisTemplate redisTemplate;

    private static final String PREFIX = "prefix";

    @GetMapping("/{key}/{value}")
    public String set(@PathVariable String key,@PathVariable String value){

        redisTemplate.opsForValue().set(key,value);

        return "success";
    }

    @GetMapping("/{key}")
    public String get(@PathVariable String key){

        return redisTemplate.opsForValue().get(key).toString();
    }


}
