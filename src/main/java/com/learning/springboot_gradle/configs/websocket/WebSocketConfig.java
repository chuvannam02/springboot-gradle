package com.learning.springboot_gradle.configs.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * @Project: springboot-gradle
 * @Package: com.learning.springboot_gradle.configs.websocket  *
 * @Author: ChuVanNam
 * @Date: 11/17/2025
 * @Time: 12:51 PM
 */

// Cấu hnh Websocket STOMP ở đây
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/ws")
				.setAllowedOriginPatterns("*")
				.withSockJS(); // Optional: hỗ trợ fallback cho client cũ
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		registry.enableSimpleBroker("/topic", "/queue");  // nơi client subscribe
		registry.setApplicationDestinationPrefixes("/app"); // nơi client gửi
	}
}
