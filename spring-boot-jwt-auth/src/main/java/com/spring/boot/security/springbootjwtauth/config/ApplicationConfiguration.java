package com.spring.boot.security.springbootjwtauth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationConfiguration {
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	@Scope("prototype")
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}
}
