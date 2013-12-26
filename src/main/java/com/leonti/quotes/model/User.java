package com.leonti.quotes.model;

public class User {

	private final String email;
	private final String hash;
	private final long registered;
	
	public User(String email, String hash, long registered) {
		this.email = email;
		this.hash = hash;
		this.registered = registered;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getHash() {
		return hash;
	}
	
	public long getRegistered() {
		return registered;
	}
}
