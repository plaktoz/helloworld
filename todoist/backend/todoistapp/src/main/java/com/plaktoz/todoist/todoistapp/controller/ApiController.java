package com.plaktoz.todoist.todoistapp.controller;

import com.plaktoz.todoist.todoistapp.config.WebClientProperties;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class ApiController {

    public static final java.lang.String BACKEND = "hostedApi";
    private final WebClient webClient;
    private final WebClientProperties props;

    private static final Logger log = LoggerFactory.getLogger(ApiController.class);


    public ApiController(WebClient webClient, WebClientProperties props) {
        this.webClient = webClient;
        this.props = props;
    }

    @CircuitBreaker(name = BACKEND, fallbackMethod = "helloFallback")
    @RateLimiter(name = BACKEND)
    @Bulkhead(name = BACKEND, fallbackMethod = "helloFallback")
    @Retry(name = BACKEND)
    @TimeLimiter(name = BACKEND)
    @GetMapping("/")
    public String hello() {
        return "Hello, World!";
    }

    @CircuitBreaker(name = BACKEND, fallbackMethod = "helloFallback")
    @RateLimiter(name = BACKEND)
    @Bulkhead(name = BACKEND, fallbackMethod = "helloFallback")
    @Retry(name = BACKEND)
//    @TimeLimiter(name = BACKEND)
    @GetMapping("/call-app2")
    public Mono<String> callApp2() {
        log.info("Calling app2 at: {}", props.getCallapp2());
        return webClient.get()
                .uri(props.getCallapp2())
                .retrieve()
                .bodyToMono(String.class);
    }

    // Fallback signature must match original method + Throwable at end
    private Mono<String> helloFallback(Throwable t) {
        log.warn("Falling back for /hello due to: {}", t.toString());
        // Safe default, cached value, or message; keep it quick
        return Mono.just("Hello (fallback)");
    }
}
