package com.leonti.quotes.model;

public class User {

	private final String id;
	private final String email;
	private final long registered;
	
	public User(String id, String email, long registered) {
		this.id = id;
		this.email = email;
		this.registered = registered;
	}
	
	public String getId() {
		return id;
	}	
	
	public String getEmail() {
		return email;
	}
	
	public long getRegistered() {
		return registered;
	}
}
