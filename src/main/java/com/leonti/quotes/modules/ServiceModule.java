package com.leonti.quotes.modules;

import com.google.inject.AbstractModule;
import com.leonti.quotes.services.ImageService;
import com.leonti.quotes.services.ImageServiceImpl;
import com.leonti.quotes.services.QuoteService;
import com.leonti.quotes.services.QuoteServiceImpl;
import com.leonti.quotes.services.UserService;
import com.leonti.quotes.services.UserServiceImpl;
import com.leonti.quotes.services.WidgetService;
import com.leonti.quotes.services.WidgetServiceImpl;

public class ServiceModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(QuoteService.class).to(QuoteServiceImpl.class);
		bind(WidgetService.class).to(WidgetServiceImpl.class);
		bind(UserService.class).to(UserServiceImpl.class);
		bind(ImageService.class).to(ImageServiceImpl.class);
	}
}
