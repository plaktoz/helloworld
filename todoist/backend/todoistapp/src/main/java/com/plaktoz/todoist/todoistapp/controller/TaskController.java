package com.plaktoz.todoist.todoistapp.controller;

import com.plaktoz.todoist.task.todoistapp.domain.TaskEntity;
import com.plaktoz.todoist.task.todoistapp.service.TaskService;
import java.util.List;
import org.springframework.web.bind.annotation.*;











@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService svc;

    public TaskController(TaskService svc) { this.svc = svc; }

    @PostMapping
    public TaskEntity create(@RequestBody TaskEntity t) {
        return svc.createTask(t.getTaskSummary(), t.getStartDate(), t.getEnDate());
    }

    @GetMapping("/{id}")
    public TaskEntity one(@PathVariable Long id) { return svc.getTask(id); }

    @GetMapping
    public List<TaskEntity> all() { return svc.listTasks(); }

    @PutMapping("/{id}")
    public TaskEntity update(@PathVariable Long id, @RequestBody TaskEntity t) { return svc.updateTask(id, t); }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { svc.deleteTask(id); }
}
