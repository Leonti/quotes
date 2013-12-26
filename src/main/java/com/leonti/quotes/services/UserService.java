package com.leonti.quotes.services;

import com.leonti.quotes.model.User;

public interface UserService {

	User create(String email);

	User read(String email);
	
	User readByHash(String hash);
	
}
