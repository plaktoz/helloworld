package com.plaktoz.todoist.todoistapp.controller;

import com.plaktoz.todoist.todoistapp.domain.TaskEntity;
import com.plaktoz.todoist.todoistapp.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    // creating a logger
    private static final Logger log
            = LoggerFactory.getLogger(TaskController.class);

    private final TaskService service;

    public TaskController(TaskService service) {
        log.info("Instantiate bean");
        this.service = service;
    }

    @PostMapping
    public TaskEntity create(@RequestBody TaskEntity t) {
        log.info("Create task");
        return service.createTask(t.getTaskSummary(), t.getStartDate(), t.getEnDate());
    }

    @GetMapping("/{id}")
    public TaskEntity one(@PathVariable Long id) {
        log.info("Select 1 task");
        return service.getTask(id);
    }

    @GetMapping
    public List<TaskEntity> all() {
        log.info("List task");
        return service.listTasks();
    }

    @PutMapping("/{id}")
    public TaskEntity update(@PathVariable Long id, @RequestBody TaskEntity t) {
        log.info("Update task");
        return service.updateTask(id, t);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        log.info("Delete task {}", id);
        service.deleteTask(id);
    }
}
