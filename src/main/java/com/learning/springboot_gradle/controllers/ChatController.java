package com.learning.springboot_gradle.controllers;

import com.learning.springboot_gradle.dtos.request.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
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
public class ChatController {
	// Client gửi vào /app/chat
	@MessageMapping("/chat")
	@SendTo("/topic/messages")
	public ChatMessage send(ChatMessage message) {
		return message; // broadcast tới tất cả client đang subscribe
	}
}
