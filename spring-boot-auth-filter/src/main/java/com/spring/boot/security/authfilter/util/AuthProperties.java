package com.spring.boot.security.authfilter.util;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="auth.server.info")
public class AuthProperties {

	private String auth_server_url = "http://127.0.0.1:9876";

	public String getAuth_server_url() {
		return auth_server_url;
	}

	public void setAuth_server_url(String auth_server_url) {
		this.auth_server_url = auth_server_url;
	}
}
