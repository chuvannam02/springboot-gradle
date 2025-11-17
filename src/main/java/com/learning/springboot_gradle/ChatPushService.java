package com.learning.springboot_gradle;

import com.learning.springboot_gradle.dtos.request.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

/**
 * @Project: springboot-gradle
 * @Package: com.learning.springboot_gradle  *
 * @Author: ChuVanNam
 * @Date: 11/17/2025
 * @Time: 12:56 PM
 */

// Từ backend chủ động push message
@Service
@RequiredArgsConstructor
public class ChatPushService {
	private final SimpMessagingTemplate template;

	// Dùng @RequiredArgsConstructor sẽ tự tạo constructor
//	public ChatPushService(SimpMessagingTemplate template) {
//		this.template = template;
//	}

	public void pushSystemMessage(String text) {
		ChatMessage msg = new ChatMessage();
		msg.setSender("SYSTEM");
		msg.setContent(text);

		template.convertAndSend("/topic/messages", msg);
	}
}
