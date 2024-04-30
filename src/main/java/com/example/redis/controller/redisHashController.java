package com.example.redis.controller;

import com.example.redis.service.RedisService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/redis/hash")
@Tag(name = "Hash", description = "redis hash")
@RequiredArgsConstructor
public class redisHashController {

    private final RedisService redisService;

    @PostMapping
    @Operation(summary = "redisKey 를 기준으로 HASH _ ADD")
    public void addWithHash(@RequestParam("redisKey") String redisKey,
                            @RequestParam("key") String key,
                            @RequestParam("value") String value){
        redisService.addWithHash(redisKey, key, value);
    }

    @DeleteMapping
    @Operation(summary = "redisKey 를 기준으로 HASH _ DELETE")
    public void removeWithHash(@RequestParam("redisKey") String redisKey,
                               @RequestParam("key") String key){
        redisService.removeWithHash(redisKey, key);
    }

    @GetMapping("/isContain")
    @Operation(summary = "redisKey 를 기준으로 HASH _ CONTAIN")
    public boolean isContainWithHash(@RequestParam("redisKey") String redisKey,
                                     @RequestParam("key") String key){
        return redisService.isContainWithHash(redisKey, key);
    }

    @GetMapping
    @Operation(summary = "redisKey 를 기준으로 HASH _ GET")
    public String getWithHash(@RequestParam("redisKey") String redisKey,
                              @RequestParam("key") String key){
        return redisService.getWithHash(redisKey, key);
    }

    @GetMapping("/all")
    @Operation(summary = "redisKey 를 기준으로 HASH _ GET _ ALL")
    public Map<String, String> getWithHashAll(@RequestParam("redisKey") String redisKey){
        return redisService.getWithHashAll(redisKey);
    }
}
