package com.baayso.springboot.config;

import java.io.Serializable;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.baayso.commons.serialize.redis.CustomSerializationRedisSerializer;

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
    public RedisTemplate<String, Serializable> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Serializable> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        template.setKeySerializer(template.getStringSerializer());
        template.setHashKeySerializer(template.getStringSerializer());
        template.setDefaultSerializer(new CustomSerializationRedisSerializer());
        template.afterPropertiesSet();

        return template;
    }

}
