package com.learning.springboot_gradle.controllers;

import com.learning.springboot_gradle.dtos.request.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Project: springboot-gradle
 * @Package: com.learning.springboot_gradle.controllers  *
 * @Author: ChuVanNam
 * @Date: 11/17/2025
 * @Time: 12:55 PM
 */

// Controller nhận + broadcast tin nhắn
@RestController
@RequiredArgsConstructor
public class ChatController {
	private final SimpMessagingTemplate messagingTemplate;
	// Client gửi vào /app/chat
//	@MessageMapping("/chat")
//	@SendTo("/topic/messages")
//	public ChatMessage send(ChatMessage message) {
//		return message; // broadcast tới tất cả client đang subscribe
//	}

	@MessageMapping("/chat")
	public void chat(ChatMessage msg) {
		messagingTemplate.convertAndSend("/topic/messages", msg);
	}

	@MessageMapping("/ping")
	public void ping() {
		messagingTemplate.convertAndSend("/topic/pong", "pong");
	}
}
