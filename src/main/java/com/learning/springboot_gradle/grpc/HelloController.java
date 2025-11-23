package com.learning.springboot_gradle.grpc;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Project: springboot-gradle
 * @Package: com.learning.springboot_gradle.grpc  *
 * @Author: ChuVanNam
 * @Date: 11/17/2025
 * @Time: 3:30 PM
 */

@RestController
public class HelloController {

	private final GrpcClient grpcClient;

	public HelloController(GrpcClient grpcClient) {
		this.grpcClient = grpcClient;
	}

	@GetMapping("/hello")
	public String hello(@RequestParam String name) {
		return grpcClient.sayHello(name);
	}
}