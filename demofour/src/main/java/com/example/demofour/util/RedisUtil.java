package com.example.demofour.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisCluster;

@Component
public class RedisUtil {
    @Autowired
    private JedisCluster jedisCluster;

    public void setObject(String key,String value){
        jedisCluster.set(key,value);

    }
    public String getObject(String key){
        return jedisCluster.get(key);
    }
}
