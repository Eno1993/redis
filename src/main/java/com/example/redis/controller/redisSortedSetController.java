package com.example.redis.controller;

import com.example.redis.service.RedisService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/redis/sortedSet")
@Tag(name = "sortedSet", description = "redis sortedSet")
@RequiredArgsConstructor
public class redisSortedSetController {

    private final RedisService redisService;

    @PostMapping
    @Operation(summary = "redisKey 를 기준으로 SORTED _ SET _ ADD")
    public void addWithSortedSet(@RequestParam("redisKey") String redisKey,
                                 @RequestParam("data") String data){
        redisService.addWithSortedSet(redisKey, data);
    }

    @DeleteMapping
    @Operation(summary = "redisKey 를 기준으로 SORTED _ SET _ DELETE")
    public void removeWithSortedSet(@RequestParam("redisKey") String redisKey,
                                    @RequestParam("data") String data){
        redisService.removeWithSortedSet(redisKey, data);
    }

    @GetMapping("/isContain")
    @Operation(summary = "redisKey 를 기준으로 SORTED _ SET _ CONTAIN")
    public boolean isContainWithSortedSet(@RequestParam("redisKey") String redisKey,
                                          @RequestParam("data") String data){
        return redisService.isContainWithSortedSet(redisKey, data);
    }

    @GetMapping("/order")
    @Operation(summary = "redisKey 를 기준으로 SORTED _ SET _ ORDER")
    public long orderWithSortedSet(@RequestParam("redisKey") String redisKey,
                                   @RequestParam("data") String data){
        return redisService.orderWithSortedSet(redisKey, data);
    }

    @GetMapping
    @Operation(summary = "redisKey 를 기준으로 SORTED _ SET _ GET _ ALL")
    public Set<String> getSortedSet(@RequestParam("redisKey") String redisKey){
        return redisService.getSortedSet(redisKey);
    }
}
