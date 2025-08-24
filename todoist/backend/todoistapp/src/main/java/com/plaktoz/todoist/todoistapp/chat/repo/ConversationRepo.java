package com.plaktoz.todoist.todoistapp.chat.repo;

import com.plaktoz.todoist.todoistapp.chat.entity.Conversation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ConversationRepo extends MongoRepository<Conversation, String> {
    List<Conversation> findByParticipantsUserId(String userId);
}
