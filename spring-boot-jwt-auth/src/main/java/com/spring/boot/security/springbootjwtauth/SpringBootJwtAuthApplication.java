package com.spring.boot.security.springbootjwtauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringBootJwtAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJwtAuthApplication.class, args);
	}

}

