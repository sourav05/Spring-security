package com.spring.boot.security.authfilter.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import com.spring.boot.security.authfilter.util.Constants;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(value=com.spring.boot.security.authfilter.config.AuthConfiguration.class)
public @interface EnableSecurity {

}
