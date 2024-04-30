package com.example.redis.controller;

import com.example.redis.service.RedisService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/redis")
@Tag(name = "redisCache", description = "redis redisCache")
@RequiredArgsConstructor
public class redisController {

    private final RedisService redisService;

    @GetMapping
    @Operation(summary = "redisCache 확인")
    public boolean isContainCache(@RequestParam("redisKey") String redisKey){
        return redisService.isContainCache(redisKey);
    }

    @DeleteMapping
    @Operation(summary = "redisCache 삭제")
    public void deleteCache(@RequestParam("redisKey") String redisKey){
        redisService.deleteCache(redisKey);
    }









}
