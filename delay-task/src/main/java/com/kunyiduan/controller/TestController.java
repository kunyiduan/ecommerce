package com.kunyiduan.controller;

import com.kunyiduan.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/create")
    public Result create(@RequestParam("code") String code) {
        redisTemplate.opsForValue().set("test:" + code, "1", 66, TimeUnit.SECONDS);
        return Result.suc();
    }

}
