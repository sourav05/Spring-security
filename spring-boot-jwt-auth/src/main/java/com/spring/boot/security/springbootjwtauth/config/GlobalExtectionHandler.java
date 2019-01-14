package com.spring.boot.security.springbootjwtauth.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExtectionHandler {

	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<String> handleException(){
		return new ResponseEntity<String>("User not authorized", HttpStatus.FORBIDDEN);
	}
}
