package com.demo.websocket.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

	@Autowired
	private SimpMessageSendingOperations messagingTemplate;

//	@MessageMapping("/message")
//	@SendTo("/topic/return-to")
//	public ChatMessage processMessage(ChatMessage message) {
//
//		return new ChatMessage().setMessage("echo " + message.getMessage());
//	}

	@MessageMapping("/message")
	public void processMessage(ChatMessage chatMessage) {
		System.out.println(chatMessage);
//		sessionId = chatMessage.getClientId();
		messagingTemplate.convertAndSend("/topic/public/"+chatMessage.getClientId(), "echo "+chatMessage.getMessage());
	}
}
