package com.leonti.quotes.modules;

import javax.inject.Named;
import javax.servlet.ServletContext;

import org.apache.shiro.guice.web.ShiroWebModule;

import com.google.inject.Key;
import com.google.inject.Provides;
import com.leonti.quotes.Configuration.Constants;
import com.leonti.quotes.auth.Exception401Filter;
import com.leonti.quotes.auth.PersonaRealm;

public class ShiroModule extends ShiroWebModule {
	
	public static final Key<Exception401Filter> AUTHC = Key.get(Exception401Filter.class);

	public ShiroModule(ServletContext servletContext) {
		super(servletContext);
	}

	@SuppressWarnings("unchecked")
	protected void configureShiroWeb() {
		bindRealm().to(PersonaRealm.class);

		addFilterChain("/rest/user/login/**", ANON);
		addFilterChain("/rest/**", AUTHC);
	}
	
	@Provides
	@Named("audience")
	public String audience(@Named(Constants.PERSONA_AUDIENCE) String audience) {
		return audience;
	}
}        
