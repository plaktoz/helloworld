package com.plaktoz.todoist.todoistapp.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.plaktoz.todoist.todoistapp.controller.ApiController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@ConditionalOnProperty(prefix = "app.redis", name = "enabled", havingValue = "true")
public class RedisConfig {

    private static final Logger log = LoggerFactory.getLogger(RedisConfig.class);

    public RedisConfig(org.springframework.core.env.Environment env) {
        System.out.println("app.redis.enabled = " + env.getProperty("app.redis.enabled"));
    }

    /***
     * Can move this out
     * @return
     */
    @Bean
    public ObjectMapper redisObjectMapper() {
        log.info("Create redisObjectMapper");
        return new ObjectMapper()
                .findAndRegisterModules(); // JavaTimeModule for LocalDateTime
    }

    @Primary
    @Bean
//    @ConditionalOnBean(org.springframework.data.redis.connection.RedisConnectionFactory.class)
//    @ConditionalOnProperty(name = "app.redis.enabled", havingValue = "true")
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory cf) {
        log.info("Create redisTemplate");
        RedisTemplate<String, String> template = new RedisTemplate<>();
        template.setConnectionFactory(cf);

        // Store keys as plain strings
        StringRedisSerializer stringSerializer = new StringRedisSerializer();
        template.setKeySerializer(stringSerializer);
        template.setHashKeySerializer(stringSerializer);

        // Store values as JSON instead of Java serialization
        GenericJackson2JsonRedisSerializer jsonSerializer = new GenericJackson2JsonRedisSerializer();
        template.setValueSerializer(jsonSerializer);
        template.setHashValueSerializer(jsonSerializer);

        template.afterPropertiesSet();
        return template;
    }
}