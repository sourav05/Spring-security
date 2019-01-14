package com.spring.boot.security.springbootjwtauth.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.boot.security.springbootjwtauth.entity.user.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

	public UserEntity findByUsername(String username);
	
	public List<UserEntity> findByActive(boolean isActive);
}
