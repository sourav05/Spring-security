package com.spring.boot.security.authfilter.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class FilterController {

	/*@PostMapping(value="/register")
	public ResponseEntity<String> register(){
		System.out.println();
		//		UserEntity userEntity = service.register(user);
		if(userEntity != null)
			return user;
		return new ResponseEntity<String>("Successfully registered", HttpStatus.OK);
	}*/
	/*
	@GetMapping(value="/login")
	public ResponseEntity<String> login(HttpHeaders authHeader){
		System.out.println(authHeader);
		//		UserEntity userEntity = service.register(user);
		if(userEntity != null)
			return user;
		return new ResponseEntity<String>("Auth Header", HttpStatus.OK);
	}*/
}
