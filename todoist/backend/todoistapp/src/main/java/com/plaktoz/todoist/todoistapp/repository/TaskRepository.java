package com.plaktoz.todoist.task.todoistapp.repository;

import com.plaktoz.todoist.task.todoistapp.domain.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;




@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
}