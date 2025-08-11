package com.plaktoz.todoist.adminapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
public class TodoistAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodoistAdminApplication.class, args);
    }

    @GetMapping("/")
    public String helloRoot() {
        return "adminapp root!";
    }

    @GetMapping("/hello")
    public String hello() {
        return "adminapp Hello, World!";
    }

}
