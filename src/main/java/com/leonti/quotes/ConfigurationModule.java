package com.leonti.quotes;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

public class ConfigurationModule extends AbstractModule {

	@Override
	protected void configure() {
		Names.bindProperties(binder(), Configuration.load());		
	}

}
