package com.example.redis.controller;


import com.example.redis.service.RedisService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/redis/list")
@Tag(name = "List", description = "redis list")
@RequiredArgsConstructor
public class redisListController {

    private final RedisService redisService;

    @PostMapping
    @Operation(summary = "redisKey 를 기준으로 LIST _ ADD")
    public void addWithList(@RequestParam("redisKey") String redisKey,
                            @RequestParam("data") String data){
        redisService.addWithList(redisKey, data);
    }

    @DeleteMapping
    @Operation(summary = "redisKey 를 기준으로 LIST _ DELETE")
    public void deleteWithList(@RequestParam("redisKey") String redisKey,
                               @RequestParam("data") String data){
        redisService.removeWithList(redisKey, data);
    }

    @GetMapping
    @Operation(summary = "redisKey 를 기준으로 LIST _ GET _ ALL")
    public List<String> getList(@RequestParam("redisKey") String redisKey){
        return redisService.getList(redisKey);
    }
}
