package com.spring.boot.security.authfilter.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.boot.security.authfilter.entity.UserEntity;
import com.spring.boot.security.authfilter.external.ExternalApi;
import com.spring.boot.security.authfilter.util.Constants;

@WebFilter(urlPatterns={"/user/register","/user/login"})
public class UserFilter implements Filter {

	private static final String LOGIN_URL = "/user/login";
	private ExternalApi external;

	public UserFilter(ExternalApi external) {
		this.external = external;
	}
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		String url = request.getRequestURL().toString();
		if(url.contains(Constants.SIGN_UP_URL)){
			UserEntity entity = new ObjectMapper().readValue(req.getInputStream(), UserEntity.class);
			entity = external.register(entity);
			response.setHeader(Constants.HEADER_STRING_USER, entity.getUsername());
			response.setHeader(Constants.HEADER_STRING_USERID, String.valueOf(entity.getId()));
		}else if(url.contains(LOGIN_URL)){
			String token = external.authenticate(request, response);
			System.out.println(response.getHeader(Constants.HEADER_STRING_AUTH));
			response.setHeader(Constants.HEADER_STRING_AUTH, token);
		}
	}
}
