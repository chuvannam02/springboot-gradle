package com.learning.springboot_gradle;

import org.springframework.boot.SpringApplication;

public class TestSpringbootGradleApplication {

	public static void main(String[] args) {
		SpringApplication.from(SpringbootGradleApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
