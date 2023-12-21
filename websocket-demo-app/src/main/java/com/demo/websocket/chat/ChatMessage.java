package com.demo.websocket.chat;

public class ChatMessage {
	private String clientId;

	

	private String message;

	// Constructors
	public ChatMessage() {
	}

	public ChatMessage(String message) {
		this.message = message;
	}
	
	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	// Getter and Setter
	public String getMessage() {
		return message;
	}

	public ChatMessage setMessage(String message) {
		this.message = message;
		return this;
	}

	@Override
	public String toString() {
		return "ChatMessage [clientId=" + clientId + ", message=" + message + "]";
	}
	
}
