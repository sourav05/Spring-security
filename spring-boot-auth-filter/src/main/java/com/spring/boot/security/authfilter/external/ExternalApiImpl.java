package com.spring.boot.security.authfilter.external;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.boot.security.authfilter.entity.UserEntity;
import com.spring.boot.security.authfilter.util.AuthProperties;
import com.spring.boot.security.authfilter.util.Constants;

public class ExternalApiImpl implements ExternalApi {

	@Autowired
	private AuthProperties properties;
	
	private RestTemplate template;

	public ExternalApiImpl() {
		template = new RestTemplate();
	}

	@Override
	public String authenticate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		UserEntity uEntity = new ObjectMapper().readValue(request.getInputStream(), UserEntity.class);

		HttpEntity<UserEntity> entity = new HttpEntity<>(uEntity);
		ResponseEntity<HttpServletResponse> resp = template.exchange(properties.getAuth_server_url() + Constants.LOGIN_URL,HttpMethod.POST, entity, HttpServletResponse.class);
		return resp.getHeaders().getFirst(Constants.HEADER_STRING_AUTH);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public HttpStatus authorize(HttpServletRequest request, HttpServletResponse response, String authToken) throws IOException {
		if(request.getRequestURL().toString().contains(Constants.SIGN_UP_URL)
				|| request.getRequestURL().toString().contains(Constants.LOGIN_URL))
			return HttpStatus.OK;
		if(authToken == null || authToken.equalsIgnoreCase(""))
			return HttpStatus.FORBIDDEN;
		HttpHeaders headers = new HttpHeaders();
		headers.add(Constants.HEADER_STRING_AUTH, authToken);

		HttpEntity entity = new HttpEntity(headers);
		try{
			ResponseEntity<HttpStatus> resp = template.exchange(properties.getAuth_server_url() + Constants.VALIDATE_URL, 
					HttpMethod.GET, entity, HttpStatus.class);
			if(resp.getStatusCode() != null){
				return resp.getStatusCode();
			}
		}catch(HttpClientErrorException e){
			System.out.println(e.getRawStatusCode());
			return HttpStatus.valueOf(e.getRawStatusCode());
		}
		return HttpStatus.UNPROCESSABLE_ENTITY;
	}

	@Override
	public UserEntity register(UserEntity user) {
		HttpEntity<UserEntity> entity = new HttpEntity<UserEntity>(user);
		ResponseEntity<UserEntity> response = template.exchange(properties.getAuth_server_url() + Constants.SIGN_UP_URL, 
				HttpMethod.POST, entity, UserEntity.class);
		if(response != null)
			return response.getBody();
		throw new RuntimeException("Unable to register user...!!!");
	}
}
