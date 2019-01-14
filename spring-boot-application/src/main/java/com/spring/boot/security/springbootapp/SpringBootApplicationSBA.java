package com.spring.boot.security.springbootapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.spring.boot.security.authfilter.security.EnableSecurity;

@SpringBootApplication
@ComponentScan(basePackages={"com.spring.boot.security.springbootapp"})
//@EnableSecurity
public class SpringBootApplicationSBA {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootApplicationSBA.class, args);
	}

}

