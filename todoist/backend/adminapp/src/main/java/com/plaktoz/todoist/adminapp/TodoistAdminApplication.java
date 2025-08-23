package com.plaktoz.todoist.adminapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
@RestController
public class TodoistAdminApplication {

    private static final Logger log
            = LoggerFactory.getLogger(TodoistAdminApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(TodoistAdminApplication.class, args);
    }

    @GetMapping("/")
    public String helloRoot() {
        return "adminapp root!";
    }

    @GetMapping("/hello")
    public String hello() {
        log.info("say hello");
        return "adminapp Hello, World!";
    }

}
