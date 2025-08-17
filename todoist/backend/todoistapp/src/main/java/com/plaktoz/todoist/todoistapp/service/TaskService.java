package com.plaktoz.todoist.todoistapp.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.plaktoz.todoist.todoistapp.domain.TaskEntity;
import com.plaktoz.todoist.todoistapp.repository.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;


@Service
@Transactional // default: write transactions; we’ll override reads with readOnly
public class TaskService {

    // creating a logger
    private static final Logger log
            = LoggerFactory.getLogger(TaskService.class);

    private static final String KEY_PREFIX = "task:";           // task:{id}
    private static final long TTL_SECONDS = 86400;              // 24 hours

    private final TaskRepository repo;

    private final RedisTemplate<String, String> redis;
    private final ObjectMapper mapper;


    public TaskService(TaskRepository repo, RedisTemplate<String, String> redis, ObjectMapper mapper) {
        this.repo = repo;
        this.redis = redis;
        this.mapper = mapper;
    }

    private String key(Long id) {
        return KEY_PREFIX + id;
    }

    private void cachePut(TaskEntity t) {
        try {
            String k = key(t.getId());
            String json = mapper.writeValueAsString(t);
            log.debug("Redis put task with key {}", k);
            redis.opsForValue().set(k, json, Duration.ofSeconds(TTL_SECONDS));
        } catch (Exception e) {
            // log and continue; DB remains source of truth
            log.warn("Failed to cache task {}", t.getId(), e);
        }
    }

    private void cacheUpdate(TaskEntity t) {
        try {
            String k = key(t.getId());
            String json = mapper.writeValueAsString(t);
            log.debug("Redis update task with key {}", k);
            redis.opsForValue()
                    .setIfPresent(k, json, Duration.ofSeconds(TTL_SECONDS));
        } catch (Exception e) {
            // log and continue; DB remains source of truth
            log.warn("Failed to cache task {}", t.getId(), e);
        }
    }

    /**
     * Create
     */
    public TaskEntity createTask(
            @NotBlank @Size(max = 255) String taskSummary,
            LocalDateTime startDate,
            LocalDateTime enDate
    ) {
        log.debug("Create task");
        TaskEntity t = new TaskEntity();
        t.setTaskSummary(taskSummary);
        t.setStartDate(startDate);
        t.setEnDate(enDate);
        log.debug("Saving record");
        TaskEntity saved = repo.save(t);
        log.debug("Putting cache");
        cachePut(saved);
        return saved;
    }

    /**
     * Read one
     */
    @Transactional(readOnly = true)
    public TaskEntity getTask(Long id) {
        log.debug("Get task");
        TaskEntity cache = getTaskFromCache( id);
        if(cache !=null){
            return cache;
        }
//        String k = key(id);
//        String cached = redis.opsForValue().get(k);
//        if (cached != null) {
//            try {
//                log.debug("Read task from cache {}", id);
//                return mapper.readValue(cached, TaskEntity.class);
//            } catch (Exception ignored) { /* fall through to DB */
//                log.warn("Failed to cache task {}", id, ignored);
//            }
//        }
        TaskEntity db = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task not found: " + id));
        cachePut(db); // populate cache
        return db;
    }

    private TaskEntity getTaskFromCache(Long id){
        String k = key(id);
        String cached = redis.opsForValue().get(k);
        if (cached != null) {
            try {
                log.debug("Read task from cache {}", id);
                return mapper.readValue(cached, TaskEntity.class);
            } catch (Exception ignored) { /* fall through to DB */
                log.warn("Failed to cache task {}", id, ignored);
            }
        }
        return null;
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
        cacheUpdate(changes);
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
        String k = key(id);
        redis.opsForValue().getAndDelete(k);
        repo.deleteById(id); // write tx
    }
}
