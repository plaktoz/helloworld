package com.plaktoz.todoist.todoistapp.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper
                .findAndRegisterModules()
                .disable(com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);// JavaTimeModule for LocalDateTime
//                .activateDefaultTyping(objectMapper.getPolymorphicTypeValidator(), ObjectMapper.DefaultTyping.NON_FINAL);
    }

    @Primary
    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory cf) {
        log.info("Create redisTemplate");

        RedisTemplate<String, String> template = new RedisTemplate<>();
        template.setConnectionFactory(cf);

        // Store keys as plain strings
        StringRedisSerializer stringSerializer = new StringRedisSerializer();
        template.setKeySerializer(stringSerializer);
        template.setHashKeySerializer(stringSerializer);

        // Store values as JSON instead of Java serialization
        GenericJackson2JsonRedisSerializer jsonSerializer = new GenericJackson2JsonRedisSerializer(redisObjectMapper());

        template.setValueSerializer(jsonSerializer);
        template.setHashValueSerializer(jsonSerializer);

        template.afterPropertiesSet();
        return template;
    }
}