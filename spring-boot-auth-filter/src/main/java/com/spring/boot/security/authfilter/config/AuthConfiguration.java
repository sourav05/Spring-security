package com.spring.boot.security.authfilter.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import com.spring.boot.security.authfilter.external.ExternalApi;
import com.spring.boot.security.authfilter.external.ExternalApiImpl;
import com.spring.boot.security.authfilter.security.AuthFilter;
import com.spring.boot.security.authfilter.security.UserFilter;
import com.spring.boot.security.authfilter.util.AuthProperties;
import com.spring.boot.security.authfilter.util.Constants;

@Configuration
@ComponentScan("com.spring.boot.security.authfilter")
public class AuthConfiguration {
	
//	@Bean
//	public BCryptPasswordEncoder bCryptPasswordEncoder(){
//		return new BCryptPasswordEncoder();
//	}
	
	@Bean
	public FilterRegistrationBean<AuthFilter> registerApplicationFilter(){
	    FilterRegistrationBean<AuthFilter> registrationBean = new FilterRegistrationBean<>();
	    registrationBean.setFilter(getAuthFilter());
	    registrationBean.addUrlPatterns("/*");
	    return registrationBean;
	}
	
	@Bean
	public FilterRegistrationBean<UserFilter> registerUserRegistrationFilter(){
	    FilterRegistrationBean<UserFilter> registrationBean = new FilterRegistrationBean<>();
	    registrationBean.setFilter(getUserFilter());
	    registrationBean.addUrlPatterns(new String[]{Constants.SIGN_UP_URL, Constants.LOGIN_URL});
	    return registrationBean;
	}
	
	@Bean
	public AuthProperties getAuthProperties(){
		return new AuthProperties();
	}
	
	@Bean
	public UserFilter getUserFilter(){
		return new UserFilter(getExternalApi());
	}
	
	@Bean
	@DependsOn("getAuthProperties")
	public ExternalApi getExternalApi(){
		return new ExternalApiImpl();
	}
	
	@Bean
	public AuthFilter getAuthFilter(){
		return new AuthFilter(getExternalApi());
	}
}
