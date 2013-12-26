package com.leonti.quotes.modules;

import com.google.inject.AbstractModule;
import com.leonti.quotes.services.QuoteService;
import com.leonti.quotes.services.QuoteServiceImpl;
import com.leonti.quotes.services.UserService;
import com.leonti.quotes.services.UserServiceImpl;

public class ServiceModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(QuoteService.class).to(QuoteServiceImpl.class);
		bind(UserService.class).to(UserServiceImpl.class);
	}
}
