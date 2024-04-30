package com.example.redis.controller;

import com.example.redis.service.RedisService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/redis/set")
@Tag(name = "Set", description = "redis set")
@RequiredArgsConstructor
public class redisSetController {

    private final RedisService redisService;

    @PostMapping
    @Operation(summary = "redisKey 를 기준으로 SET _ ADD")
    public void addWithSet(@RequestParam("redisKey") String redisKey,
                           @RequestParam("data") String data){
        redisService.addWithSet(redisKey, data);
    }

    @DeleteMapping
    @Operation(summary = "redisKey 를 기준으로 SET _ DELETE")
    public void removeWithSet(@RequestParam("redisKey") String redisKey,
                              @RequestParam("data") String data){
        redisService.removeWithSet(redisKey, data);
    }

    @GetMapping("/isContain")
    @Operation(summary = "redisKey 를 기준으로 SET _ CONTAIN")
    public boolean isContainWithSet(@RequestParam("redisKey") String redisKey,
                                    @RequestParam("data") String data){
        return redisService.isContainWithSet(redisKey, data);
    }

    @GetMapping("/default")
    @Operation(summary = "redisKey 를 기준으로 SET _ GET _ ALL")
    public Set<String> getSet(@RequestParam("redisKey") String redisKey){
        return redisService.getSet(redisKey);
    }

    @GetMapping ("/scan")
    @Operation(summary = "redisKey 를 기준으로 SET _ GET _ ALL _ SCAN")
    public Set<String> getSetWithScan(@RequestParam("redisKey") String redisKey){
        return redisService.getSetWithScan(redisKey);
    }
}
