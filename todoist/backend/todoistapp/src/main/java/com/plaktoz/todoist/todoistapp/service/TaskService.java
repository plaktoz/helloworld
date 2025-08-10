package com.plaktoz.todoist.task.todoistapp.service;

import com.plaktoz.todoist.task.todoistapp.domain.TaskEntity;
import com.plaktoz.todoist.task.todoistapp.repository.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;






@Service
@Transactional // default: write transactions; we’ll override reads with readOnly
public class TaskService {

    private final TaskRepository repo;

    public TaskService(TaskRepository repo) {
        this.repo = repo;
    }

    /** Create */
    public TaskEntity createTask(
            @NotBlank @Size(max = 255) String taskSummary,
            LocalDateTime startDate,
            LocalDateTime enDate
    ) {
        TaskEntity t = new TaskEntity();
        t.setTaskSummary(taskSummary);
        t.setStartDate(startDate);
        t.setEnDate(enDate);
        return repo.save(t); // within a write tx
    }

    /** Read one */
    @Transactional(readOnly = true)
    public TaskEntity getTask(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task not found: " + id));
    }

    /** Read all */
    @Transactional(readOnly = true)
    public List<TaskEntity> listTasks() {
        return repo.findAll();
    }

    /** Update (partial) */
    public TaskEntity updateTask(Long id, @Valid TaskEntity changes) {
        TaskEntity current = getTask(id); // read inside tx
        if (changes.getTaskSummary() != null) current.setTaskSummary(changes.getTaskSummary());
        if (changes.getStartDate() != null)   current.setStartDate(changes.getStartDate());
        if (changes.getEnDate() != null)      current.setEnDate(changes.getEnDate());
        return repo.save(current); // still within the same tx
    }

    /** Delete */
    public void deleteTask(Long id) {
        if (!repo.existsById(id)) {
            throw new EntityNotFoundException("Task not found: " + id);
        }
        repo.deleteById(id); // write tx
    }
}
