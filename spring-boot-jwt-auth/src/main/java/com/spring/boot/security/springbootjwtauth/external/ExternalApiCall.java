package com.spring.boot.security.springbootjwtauth.external;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ExternalApiCall {

	public boolean authenticate(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
