package com.spring.boot.security.springbootjwtauth.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.boot.security.springbootjwtauth.dao.UserRepository;
import com.spring.boot.security.springbootjwtauth.entity.user.UserEntity;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	/**
	 * <p>In this method integrate the way by which you want to fetch the user credentials.
	 * Such as LDAP, database or others.</p>
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity user = userRepository.findByUsername(username);
		if(user == null){
			throw new UsernameNotFoundException("No user found with username : " + username);
		}
		if(!user.isActive()){
			throw new UsernameNotFoundException("User is inactive...!!!");
		}
		UserDetails uDetails = new User(user.getUsername(), user.getPassword(), new ArrayList<>());
		return uDetails;
	}

}
