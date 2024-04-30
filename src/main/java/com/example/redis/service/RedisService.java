package com.example.redis.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class RedisService {

    private final RedisTemplate<String, Object> redisTemplate;

    public void addWithList(String redisKey, String data){
        redisTemplate.opsForList().rightPush(redisKey, data);
    }

    public void removeWithList(String redisKey, String data){
        redisTemplate.opsForList().remove(redisKey, 0, data);
    }

    public List<String> getList(String redisKey){
        List<Object> objectList = redisTemplate.opsForList().range(redisKey, 0, -1);
        List<String> res = new ArrayList<>();
        for(Object o : objectList){
            res.add((String) o);
        }
        return res;
    }

    public void addWithSet(String redisKey, String data){
        redisTemplate.opsForSet().add(redisKey, data);
    }

    public void removeWithSet(String redisKey, String data){
        redisTemplate.opsForSet().remove(redisKey, data);
    }

    public boolean isContainWithSet(String redisKey, String data){
        return redisTemplate.opsForSet().isMember(redisKey, data);
    }

    public Set<String> getSet(String redisKey){
        Set<Object> objectSet = redisTemplate.opsForSet().members(redisKey);
        Set<String> res = new HashSet<>();
        for(Object o : objectSet){
            res.add((String) o);
        }
        return res;
    }

    public Set<String> getSetWithScan(String redisKey){

        ScanOptions scanOptions = ScanOptions.scanOptions().build();
        Cursor<Object> cursor = redisTemplate.opsForSet().scan(redisKey, scanOptions);

        Set<String> res = new HashSet<>();
        while (cursor.hasNext()) {
            res.add((String) cursor.next());
        }
        return res;
    }

    public void addWithSortedSet(String redisKey, String data){
        redisTemplate.opsForZSet().add(redisKey, data, System.currentTimeMillis());
    }

    public void removeWithSortedSet(String redisKey, String data){
        redisTemplate.opsForZSet().remove(redisKey, data);
    }

    public boolean isContainWithSortedSet(String redisKey, String data){
        Double score = redisTemplate.opsForZSet().score(redisKey, data);
        return score!=null;
    }

    public long orderWithSortedSet(String redisKey, String data){
        Long rank = redisTemplate.opsForZSet().rank(redisKey, data);
        if(rank!=null){
            return rank;
        }
        return -1l;
    }

    public Set<String> getSortedSet(String redisKey){
        Set<Object> objectSet = redisTemplate.opsForZSet().range(redisKey, 0, -1);
        // objectSet = redisTemplate.opsForZSet().reverseRange(redisKey, 0, -1); //역순 정렬
        Set<String> res = new HashSet<>();
        for(Object o : objectSet){
            res.add((String) o);
        }
        return res;
    }

    public void addWithHash(String redisKey, String key, String value){
        redisTemplate.opsForHash().put(redisKey, key, value);
    }

    public void removeWithHash(String redisKey, String key){
        redisTemplate.opsForHash().delete(redisKey, key);
    }

    public boolean isContainWithHash(String redisKey, String key){
        return redisTemplate.opsForHash().hasKey(redisKey, key);
    }

    public String getWithHash(String redisKey, String key){
        Object object = redisTemplate.opsForHash().get(redisKey, key);
        if(object!=null){
            return (String) object;
        }
        return "null";
    }

    public Map<String, String> getWithHashAll(String redisKey){
        Map<Object, Object> objectMap = redisTemplate.opsForHash().entries(redisKey);
        Map<String, String> res = new HashMap<>();
        for(Object o : objectMap.keySet()){
            String key = (String) o;
            String value = (String) objectMap.get(o);
            res.put(key, value);
        }
        return res;
    }

    public void deleteCache(String redisKey){
        redisTemplate.delete(redisKey);
    }

    public boolean isContainCache(String redisKey){
        return redisTemplate.hasKey(redisKey);
    }

}
