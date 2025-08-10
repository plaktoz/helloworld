package com.plaktoz.todoist.todoistapp.config;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.plaktoz.todoist.todoistapp.repository")
@EntityScan(basePackages = "com.plaktoz.todoist.todoistapp.domain")
public class DbConfig {
    
}
