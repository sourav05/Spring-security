package com.spring.boot.security.springbootapp.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExtectionHandlerSBA {

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<String> handleException(RuntimeException e){
		return new ResponseEntity<String>("Runtime Exception occured", HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
