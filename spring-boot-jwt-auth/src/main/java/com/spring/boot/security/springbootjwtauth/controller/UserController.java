package com.spring.boot.security.springbootjwtauth.controller;

import javax.transaction.NotSupportedException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boot.security.springbootjwtauth.dao.UserRepository;
import com.spring.boot.security.springbootjwtauth.entity.user.UserEntity;
import com.spring.boot.security.springbootjwtauth.service.AuthService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private AuthService authService;
	
	@PostMapping(value="/register", consumes="application/json")
	public UserEntity registerUser(@RequestBody UserEntity user){
		user = authService.registerUser(user);
		return user;
	}
	
	@PutMapping(value="/changepwd")
	public UserEntity changePassword(@RequestBody UserEntity user) throws NotSupportedException {
		if(user.getId() == 0){
			throw new NotSupportedException("User id not received. Can't update password.");
		}
		authService.changePassword(user);
		return user;
	}
	
	@GetMapping(value="/validate")
	public ResponseEntity<String> validate(){
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping(value="/login", consumes="application/json")
	public HttpStatus login(@RequestBody UserEntity user){
		return HttpStatus.OK;
	}
}
