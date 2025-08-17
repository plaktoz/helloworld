package com.plaktoz.todoist.todoistapp.controller;

import com.plaktoz.todoist.todoistapp.domain.TaskEntity;
import com.plaktoz.todoist.todoistapp.service.TaskService;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    public static final java.lang.String BACKEND = "hostedApi";

    // creating a logger
    private static final Logger log
            = LoggerFactory.getLogger(TaskController.class);

    private final TaskService service;

    public TaskController(TaskService service) {
        log.info("Instantiate bean");
        this.service = service;
    }

    @CircuitBreaker(name = BACKEND)
    @RateLimiter(name = BACKEND)
    @Bulkhead(name = BACKEND)
    @Retry(name = BACKEND)
    @PostMapping
    public TaskEntity create(@RequestBody TaskEntity t) {
        log.info("Create task");
        return service.createTask(t.getTaskSummary(), t.getStartDate(), t.getEnDate());
    }

    @CircuitBreaker(name = BACKEND)
    @RateLimiter(name = BACKEND)
    @Bulkhead(name = BACKEND)
    @Retry(name = BACKEND)
    @GetMapping("/{id}")
    public TaskEntity one(@PathVariable Long id) {
        log.info("Select 1 task");
        return service.getTask(id);
    }

    @CircuitBreaker(name = BACKEND)
    @RateLimiter(name = BACKEND)
    @Bulkhead(name = BACKEND)
    @Retry(name = BACKEND)
//    @TimeLimiter(name = BACKEND)
    @GetMapping
    public List<TaskEntity> all() {
        log.info("List task");
        return service.listTasks();
    }

    @CircuitBreaker(name = BACKEND)
    @RateLimiter(name = BACKEND)
    @Bulkhead(name = BACKEND)
    @Retry(name = BACKEND)
    @PutMapping("/{id}")
    public TaskEntity update(@PathVariable Long id, @RequestBody TaskEntity t) {
        log.info("Update task");
        return service.updateTask(id, t);
    }

    @CircuitBreaker(name = BACKEND)
    @RateLimiter(name = BACKEND)
    @Bulkhead(name = BACKEND)
    @Retry(name = BACKEND)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        log.info("Delete task {}", id);
        service.deleteTask(id);
    }

}
