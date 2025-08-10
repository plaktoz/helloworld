package com.plaktoz.todoist.todoistapp.controller;

import org.slf4j.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.plaktoz.todoist.todoistapp.config.WebClientProperties;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final WebClient webClient;
    private final WebClientProperties props;

    private static final Logger log = LoggerFactory.getLogger(ApiController.class);
    

    public ApiController(WebClient webClient, WebClientProperties props) {
        this.webClient = webClient;
        this.props = props;
    }
    
    @GetMapping("/")
    public String hello() {
        return "Hello, World!";
    }

    @GetMapping("/call-app2")
    public Mono<String> callApp2() {
        log.info("Calling app2 at: {}", props.getCallapp2());
        return webClient.get()
                .uri(props.getCallapp2())
                .retrieve()
                .bodyToMono(String.class);
    }
}
