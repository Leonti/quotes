package com.leonti.quotes.modules;

import com.google.inject.AbstractModule;
import com.leonti.quotes.services.QuoteService;
import com.leonti.quotes.services.QuoteServiceImpl;

public class ServiceModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(QuoteService.class).to(QuoteServiceImpl.class);
	}
}
