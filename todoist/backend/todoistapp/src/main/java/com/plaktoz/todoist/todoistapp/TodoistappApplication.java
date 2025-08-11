package com.plaktoz.todoist.todoistapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@ComponentScan(basePackages = {"com.plaktoz.todoist"})
@RestController
// @EnableJpaRepositories(basePackages = "com.plaktoz.todoist.todoistapp.repository")
// @EntityScan(basePackages = "com.plaktoz.todoist.todoistapp.domain")
public class TodoistappApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodoistappApplication.class, args);
    }

}
