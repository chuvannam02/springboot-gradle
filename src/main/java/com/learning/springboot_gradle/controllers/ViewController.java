package com.learning.springboot_gradle.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Project: springboot-gradle
 * @Package: com.learning.springboot_gradle.controllers  *
 * @Author: ChuVanNam
 * @Date: 11/17/2025
 * @Time: 1:09 PM
 */

@Controller
public class ViewController {

	//	✅ 6. indexv1.html (Thymeleaf SSR)
//	src/main/resources/templates/index.html
//✔ Dùng CDN SockJS + STOMP
//✔ Render từ server bằng Thymeleaf
	@GetMapping("/")
	public String home() {
		return "indexv1";   // templates/indexv1.html
	}
}