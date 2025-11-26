package com.learning.springboot_gradle.configs.scheduler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * @Project: springboot-gradle
 * @Package: com.learning.springboot_gradle.configs.scheduler  *
 * @Author: ChuVanNam
 * @Date: 11/26/2025
 * @Time: 3:50 PM
 */

@Configuration
@EnableScheduling
public class SchedulerConfig {
	@Bean
	public TaskScheduler taskScheduler() {
		ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
		// Set pool size tùy lượng request, ví dụ 5 luồng xử lý hẹn giờ song song
		scheduler.setPoolSize(5);
		scheduler.setThreadNamePrefix("DeleteScheduler-");
		scheduler.initialize();
		return scheduler;
	}
}
