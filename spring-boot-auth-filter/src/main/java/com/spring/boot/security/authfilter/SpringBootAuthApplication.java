package com.spring.boot.security.authfilter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.spring.boot.security.authfilter.security.EnableSecurity;

@SpringBootApplication
public class SpringBootAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootAuthApplication.class, args);
	}

}

