package com.plaktoz.todoist.todoistapp.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.plaktoz.todoist.todoistapp.repository")
@EntityScan(basePackages = "com.plaktoz.todoist.todoistapp.domain")
public class DbConfig {

}
