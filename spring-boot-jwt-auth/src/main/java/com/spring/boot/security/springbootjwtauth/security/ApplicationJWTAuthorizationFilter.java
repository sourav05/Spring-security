package com.spring.boot.security.springbootjwtauth.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.spring.boot.security.springbootjwtauth.util.AuthConstants;

public class ApplicationJWTAuthorizationFilter extends BasicAuthenticationFilter {

	public ApplicationJWTAuthorizationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException{
		String header = req.getHeader(AuthConstants.HEADER_STRING);
		if(header == null || !header.startsWith(AuthConstants.TOKEN_PREFIX)){
			chain.doFilter(req, resp);
			return;
		}
		
		UsernamePasswordAuthenticationToken authentication = getAuthorization(req);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(req, resp);
	}

	private UsernamePasswordAuthenticationToken getAuthorization(HttpServletRequest req) {
		String authHeader = req.getHeader(AuthConstants.HEADER_STRING);
		if(authHeader != null){
			String token = authHeader.replace(AuthConstants.TOKEN_PREFIX, "");
			String user = JWT.require(Algorithm.HMAC512(AuthConstants.SECRET.getBytes())).build().verify(token).getSubject();
			if(user != null){
				return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
			}
		}
		return null;
	}

}
