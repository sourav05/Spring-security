package com.spring.boot.security.springbootjwtauth.external;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.spring.boot.security.springbootjwtauth.util.AuthConstants;

@Component
public class ExternalApiCallImpl implements ExternalApiCall {

	@Autowired
	private RestTemplate template;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public boolean authenticate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if(request.getRequestURL().toString().contains(AuthConstants.SIGN_UP_URL))
			return true;
		String authHeader = request.getHeader(AuthConstants.HEADER_STRING);
		HttpHeaders headers = new HttpHeaders();
		headers.add(AuthConstants.HEADER_STRING, authHeader);
		
		HttpEntity entity = new HttpEntity(headers);
		
		ResponseEntity<HttpStatus> resp = template.exchange("url", HttpMethod.GET, entity, HttpStatus.class);
		if(resp.getBody() == HttpStatus.OK)
			return true;
		return false;
	}
}
