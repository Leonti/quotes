package com.leonti.quotes.services;

import javax.inject.Inject;

import com.leonti.quotes.persistence.User;
import com.leonti.quotes.persistence.dao.UserDao;

public class UserServiceImpl implements UserService {

	private final UserDao userDao;

	@Inject
	public UserServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}
	
	
	@Override
	public User create(String email) {
		return userDao.create(email);
	}

	@Override
	public User read(String email) {
		return userDao.read(email);
	}

	@Override
	public User readByHash(String hash) {
		return userDao.readByHash(hash);
	}

}