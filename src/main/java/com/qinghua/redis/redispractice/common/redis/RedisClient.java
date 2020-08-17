package com.qinghua.redis.redispractice.common.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

@Configuration
public class RedisClient {

    @Bean
    public RedisTemplate<Long, Long> customRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Long, Long> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(RedisSerializer.java());
        redisTemplate.setValueSerializer(RedisSerializer.java());
        return redisTemplate;
    }

}
