package com.spring.boot.security.authfilter.external;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;

import com.spring.boot.security.authfilter.entity.UserEntity;

public interface ExternalApi {

	public String authenticate(HttpServletRequest request, HttpServletResponse response) throws IOException;

	public HttpStatus authorize(HttpServletRequest request, HttpServletResponse response, String authToken) throws IOException ;
	
	public UserEntity register(UserEntity user);
}
