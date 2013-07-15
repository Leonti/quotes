package com.leonti.quotes.modules;

import java.util.Map;

import com.google.common.collect.Maps;
import com.leonti.quotes.CorsFilter;
import com.leonti.quotes.resources.QuoteResourceImpl;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

public class ResourceModule extends JerseyServletModule {

	@Override
	protected void configureServlets() {
		
		bind(QuoteResourceImpl.class);

		Map<String, String> params = Maps.newHashMap();
		params.put(JSONConfiguration.FEATURE_POJO_MAPPING, "true");
		
		serve("/rest/*").with(GuiceContainer.class, params);
		
		filter("/*").through(CorsFilter.class);
	}

}
