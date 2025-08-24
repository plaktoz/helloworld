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
public class JsonConfig {

    private static final Logger log = LoggerFactory.getLogger(JsonConfig.class);

    /***
     * Can move this out
     * @return
     */
    @Bean
    public ObjectMapper jsonObjectMapper() {
        log.info("Create objectMapper");
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper
                .findAndRegisterModules()
                .disable(com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);// JavaTimeModule for LocalDateTime
//                .activateDefaultTyping(objectMapper.getPolymorphicTypeValidator(), ObjectMapper.DefaultTyping.NON_FINAL);
    }

}