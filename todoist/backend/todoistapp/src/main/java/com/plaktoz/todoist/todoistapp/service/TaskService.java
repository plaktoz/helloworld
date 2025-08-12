package com.plaktoz.todoist.todoistapp.service;

import com.plaktoz.todoist.todoistapp.domain.TaskEntity;
import com.plaktoz.todoist.todoistapp.repository.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;


@Service
@Transactional // default: write transactions; we’ll override reads with readOnly
public class TaskService {

    // creating a logger
    private static final Logger log
            = LoggerFactory.getLogger(TaskService.class);

    private final TaskRepository repo;

    public TaskService(TaskRepository repo) {
        this.repo = repo;
    }

    /**
     * Create
     */
    public TaskEntity createTask(
            @NotBlank @Size(max = 255) String taskSummary,
            LocalDateTime startDate,
            LocalDateTime enDate
    ) {
        log.info("Create entity");
        TaskEntity t = new TaskEntity();
        t.setTaskSummary(taskSummary);
        t.setStartDate(startDate);
        t.setEnDate(enDate);
        return repo.save(t); // within a write tx
    }

    /**
     * Read one
     */
    @Transactional(readOnly = true)
    public TaskEntity getTask(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task not found: " + id));
    }

    /**
     * Read all
     */
    @Transactional(readOnly = true)
    public List<TaskEntity> listTasks() {
        log.info("List entity");
        return repo.findAll();
    }

    /**
     * Update
     */
    public TaskEntity updateTask(Long id, @Valid TaskEntity changes) {
        log.info("Update entity");
        TaskEntity current = getTask(id); // read inside tx
        if (changes.getTaskSummary() != null) current.setTaskSummary(changes.getTaskSummary());
        if (changes.getStartDate() != null) current.setStartDate(changes.getStartDate());
        if (changes.getEnDate() != null) current.setEnDate(changes.getEnDate());
        return repo.save(current); // still within the same tx
    }

    /**
     * Delete
     */
    public void deleteTask(Long id) {
        log.info("Delete entity");
        if (!repo.existsById(id)) {
            throw new EntityNotFoundException("Task not found: " + id);
        }
        repo.deleteById(id); // write tx
    }
}
