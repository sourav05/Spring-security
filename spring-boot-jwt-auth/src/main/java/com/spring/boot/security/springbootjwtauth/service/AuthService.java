package com.spring.boot.security.springbootjwtauth.service;

import javax.transaction.NotSupportedException;

import com.spring.boot.security.springbootjwtauth.entity.user.UserEntity;

public interface AuthService {

	public UserEntity registerUser(UserEntity user);
	
	public UserEntity changePassword(UserEntity user) throws NotSupportedException;
}
