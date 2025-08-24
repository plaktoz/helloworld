package com.plaktoz.todoist.todoistapp.chat.repo;


import com.plaktoz.todoist.todoistapp.chat.entity.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface MessageRepo extends MongoRepository<Message, String> {
    Page<Message> findByConversationIdOrderByCreatedAtAsc(String conversationId, Pageable pageable);
}