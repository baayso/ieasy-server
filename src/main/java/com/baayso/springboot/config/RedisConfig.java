package com.baayso.springboot.config;

import java.io.Serializable;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Redis配置。
 *
 * @author ChenFangjie (2016/4/12 11:44)
 * @since 1.0.0
 */
@Configuration
public class RedisConfig {

    @Bean
    @ConditionalOnMissingBean(StringRedisTemplate.class)
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory factory) {
        StringRedisTemplate template = new StringRedisTemplate(factory);
        template.afterPropertiesSet();

        return template;
    }

    @Bean
    @ConditionalOnMissingBean(name = "redisTemplate")
    public RedisTemplate<String, Serializable> redisTemplate(RedisConnectionFactory factory, ObjectMapper objectMapper) {
        Jackson2JsonRedisSerializer<Serializable> jsonSerializer = new Jackson2JsonRedisSerializer<>(Serializable.class);
        jsonSerializer.setObjectMapper(objectMapper);

        RedisTemplate<String, Serializable> template = new RedisTemplate<>();
        RedisSerializer<String> stringSerializer = template.getStringSerializer();
        template.setConnectionFactory(factory);
        template.setKeySerializer(stringSerializer);
        template.setHashKeySerializer(stringSerializer);
        template.setDefaultSerializer(jsonSerializer);
        template.afterPropertiesSet();

        return template;
    }

}
