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

import org.springframework.http.HttpStatus;

import com.spring.boot.security.authfilter.external.ExternalApi;
import com.spring.boot.security.authfilter.util.Constants;

@WebFilter(urlPatterns="/user/*")
public class AuthFilter implements Filter {

	private ExternalApi external;

	public AuthFilter(ExternalApi external) {
		this.external = external;
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		String reqUrl = request.getRequestURL().toString();
		if(reqUrl.contains(Constants.SIGN_UP_URL) || reqUrl.contains(Constants.LOGIN_URL)) {
			chain.doFilter(req, resp);
		} else {
			String authToken = request.getHeader(Constants.HEADER_STRING_AUTH);
			HttpStatus authorized = external.authorize(request, response, authToken);

			if(authorized == HttpStatus.OK){
				chain.doFilter(req, resp);
			} /*else {
				String token = external.authenticate(request, response);
				if(token != null && !token.equalsIgnoreCase("")){
					boolean proceed = external.authorize(request, response, token);
					if(proceed)
						chain.doFilter(req, resp);
				}
			}*/ else {
				response.sendError(authorized.value(), authorized.name());
			}
		}
	}

}
