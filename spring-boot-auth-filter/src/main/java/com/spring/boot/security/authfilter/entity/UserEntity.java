package com.spring.boot.security.authfilter.entity;

public class UserEntity {

	private long id;
	private String username;
	private String password;
	private boolean active = false;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	@Override
	public String toString(){
		return "ID: " + this.id + "\nUserName: " + this.username + "\nPassword: " + this.password + "\nActive: " + this.active;
	}
}
