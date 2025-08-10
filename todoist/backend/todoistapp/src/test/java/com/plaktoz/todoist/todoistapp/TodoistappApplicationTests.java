package com.plaktoz.todoist.todoistapp;

import static org.assertj.core.api.Assertions.assertThat;

import com.plaktoz.todoist.task.todoistapp.domain.TaskEntity;
import com.plaktoz.todoist.task.todoistapp.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;







@SpringBootTest(
    properties = {
        // Force H2 dialect directly
        "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect",
        "spring.datasource.url=jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1",
        "spring.datasource.driver-class-name=org.h2.Driver",
        "spring.datasource.username=sa",
        "spring.datasource.password=",
        // Disable Redis autoconfig
        "spring.autoconfigure.exclude=" +
            "org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration," +
            "org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration"
    }
)
// @EntityScan(basePackages = "com.plaktoz.todoist.todoistapp.task")
@ActiveProfiles("test") // will also load application-test.yml if present
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
