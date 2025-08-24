package com.plaktoz.todoist.todoistapp.chat.service;

import com.plaktoz.todoist.todoistapp.chat.entity.Message;
import com.plaktoz.todoist.todoistapp.chat.repo.ConversationRepo;
import com.plaktoz.todoist.todoistapp.chat.repo.MessageRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class ChatService {

    private final ConversationRepo convRepo;
    private final MessageRepo msgRepo;

    public ChatService(ConversationRepo convRepo, MessageRepo msgRepo) {
        this.convRepo = convRepo;
        this.msgRepo = msgRepo;
    }

    public Message postText(String conversationId, String senderId, String text) {
        Message msg = new Message();
        msg.setConversationId(conversationId);
        msg.setSenderId(senderId);
        msg.setType("text");
        msg.setText(text);
        msg.setCreatedAt(Instant.now());

        Message saved = msgRepo.save(msg);

        convRepo.findById(conversationId).ifPresent(conv -> {
            conv.setLastMessageId(saved.getId());
            conv.setLastMessageAt(saved.getCreatedAt());
            conv.setMessageCount(conv.getMessageCount() + 1);
            convRepo.save(conv);
        });

        return saved;
    }

    public Page<Message> listPage(String conversationId, int page, int size) {
        PageRequest pr = PageRequest.of(page, size, Sort.by("createdAt").ascending());
        return msgRepo.findByConversationIdOrderByCreatedAtAsc(conversationId, pr);
    }
}
