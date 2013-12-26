package com.leonti.quotes.auth;

import org.apache.shiro.authc.RememberMeAuthenticationToken;

@SuppressWarnings("serial")
public class PersonaToken implements RememberMeAuthenticationToken {

	private final String assertion;
	
	private String email = null;
	
	public PersonaToken(String assertion) {
		this.assertion = assertion;
	}
	
	@Override
	public Object getCredentials() {
		return assertion;
	}

	@Override
	public Object getPrincipal() {
		return email;
	}

	@Override
	public boolean isRememberMe() {
		return true;
	}
}
