package com.leonti.quotes;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.leonti.quotes.modules.PersistenceModule;
import com.leonti.quotes.modules.ResourceModule;
import com.leonti.quotes.modules.ServiceModule;

public class GuiceContextListener extends GuiceServletContextListener {

	@Override
	protected Injector getInjector() {

		Injector injector = Guice.createInjector(
				new ConfigurationModule(),
				new ResourceModule(), 
				new ServiceModule(),
				new PersistenceModule());

		return injector;
	}

}
