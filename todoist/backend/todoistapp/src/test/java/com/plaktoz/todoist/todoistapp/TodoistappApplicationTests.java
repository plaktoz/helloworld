package com.plaktoz.todoist.todoistapp;

import com.plaktoz.todoist.todoistapp.domain.TaskEntity;
import com.plaktoz.todoist.todoistapp.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest()
@ActiveProfiles("test") // will also load application-test.yml if present (does not work)
class TodoistappApplicationTests {

    @Autowired
    TaskRepository repo;

    @Test
    void canInsertAndReadTask() {
        TaskEntity task = new TaskEntity();
        task.setTaskSummary("Test task");
        TaskEntity saved = repo.save(task);

        assertThat(saved.getId()).isNotNull();
        assertThat(repo.findById(saved.getId())).isPresent();
    }
}
