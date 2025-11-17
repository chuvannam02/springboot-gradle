package com.learning.springboot_gradle.dtos.request;

import lombok.Data;

/**
 * @Project: springboot-gradle
 * @Package: com.learning.springboot_gradle.dtos.request  *
 * @Author: ChuVanNam
 * @Date: 11/17/2025
 * @Time: 12:54 PM
 */

// DTO cho tin nhắn chat qua WebSocket
@Data
public class ChatMessage {
	private String sender;
	private String content;

	// getters/setters
}
