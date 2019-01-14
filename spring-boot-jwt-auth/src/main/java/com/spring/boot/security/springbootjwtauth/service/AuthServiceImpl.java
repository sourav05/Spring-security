package com.spring.boot.security.springbootjwtauth.service;

import javax.transaction.NotSupportedException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.spring.boot.security.springbootjwtauth.dao.UserRepository;
import com.spring.boot.security.springbootjwtauth.entity.user.UserEntity;

@Service
public class AuthServiceImpl implements AuthService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public UserEntity registerUser(@RequestBody UserEntity user){
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user = userRepository.save(user);
		return user;
	}
	
	@Override
	public UserEntity changePassword(@RequestBody UserEntity user) throws NotSupportedException {
		if(user.getId() == 0){
			throw new NotSupportedException("User id not received. Can't update password.");
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
		return user;
	}
}
