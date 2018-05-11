package it.iseed.controllers;

public class UserRequest {
	
	String username;
	String password;
	
	public UserRequest() {
		super();
	}
	
	public UserRequest(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserRequest [username=" + username + ", password=" + password + "]";
	}
	
	

}
