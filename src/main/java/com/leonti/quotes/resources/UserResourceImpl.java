package com.leonti.quotes.resources;

import java.util.Map;

import javax.inject.Inject;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;

import com.leonti.quotes.auth.PersonaToken;
import com.leonti.quotes.persistence.User;
import com.leonti.quotes.services.UserService;

public class UserResourceImpl implements UserResource {
	
	private UserService userService;

	@Inject
	public UserResourceImpl(UserService userService) {
		this.userService = userService;
	}
	
	@Override
	public User get() {
		Subject currentUser = SecurityUtils.getSubject();

		String email = (String) currentUser.getPrincipal();
		return userService.read(email);
	}

	@Override
	public User login(Map<String, String> data) {
		Subject currentUser = SecurityUtils.getSubject();

		AuthenticationToken token = new PersonaToken(data.get("assertion"));		
		currentUser.login(token);
		
		String email = (String) currentUser.getPrincipal();
		return userService.read(email);
	}

	@Override
	public void logout() {
		Subject currentUser = SecurityUtils.getSubject();
		currentUser.logout();
	}
}
