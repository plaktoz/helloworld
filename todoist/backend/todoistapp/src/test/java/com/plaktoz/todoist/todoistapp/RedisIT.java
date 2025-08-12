package com.plaktoz.todoist.todoistapp;

import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.GenericContainer;

import java.io.IOException;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
class RedisIT {

    // creating a logger
    private static final Logger log
            = LoggerFactory.getLogger(RedisIT.class);

    static GenericContainer redisContainer = new GenericContainer("redis:5-alpine").withExposedPorts(6379);

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry r) throws IOException {
//        log.inf("Setup redis container {}:{}", redisContainer::getContainerIpAddress, redisContainer::getFirstMappedPort);
        r.add("spring.redis.host", redisContainer::getContainerIpAddress);
        r.add("spring.redis.port", redisContainer::getFirstMappedPort);
    }

}