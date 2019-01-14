package com.spring.boot.security.springbootjwtauth.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.boot.security.springbootjwtauth.entity.user.UserEntity;
import com.spring.boot.security.springbootjwtauth.util.AuthConstants;

public class ApplicationJWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter{

	private AuthenticationManager authenticationManager;
	
	public ApplicationJWTAuthenticationFilter(AuthenticationManager authenticationManager){
		this.authenticationManager = authenticationManager;
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, 
												HttpServletResponse resp) throws AuthenticationException{
		
		UserEntity user = null;
		Authentication auth = null;
		try {
			user = new ObjectMapper().readValue(req.getInputStream(), UserEntity.class);
			auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), 
																						user.getPassword(), 
																						new ArrayList<>()));
		} catch (IOException e) {
			throw new RuntimeException("Unable to authenticate user", e);
		}
		return auth;
	}
	
	@Override
	public void successfulAuthentication(HttpServletRequest req, 
										 HttpServletResponse resp,
										 FilterChain chain,
										 Authentication auth) throws AuthenticationException {
		String token = JWT.create().withSubject(((User) auth.getPrincipal()).getUsername())
					.withExpiresAt(new Date(System.currentTimeMillis() + AuthConstants.EXPIRATION_TIME))
					.sign(Algorithm.HMAC512(AuthConstants.SECRET.getBytes()));
		resp.addHeader(AuthConstants.HEADER_STRING, AuthConstants.TOKEN_PREFIX + token);
	}
}
