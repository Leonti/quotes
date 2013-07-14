package com.leonti.quotes;

import java.util.EnumSet;

import javax.servlet.DispatcherType;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.session.SessionHandler;
import org.eclipse.jetty.util.resource.ResourceCollection;
import org.eclipse.jetty.webapp.WebAppContext;

import com.google.inject.servlet.GuiceFilter;

public class Jetty {
	
	public static Server init(int port, GuiceContextListener guiceContextListener) {
		
		Server server = new Server(port);

		WebAppContext context = new WebAppContext();
		context.setContextPath("/");
		context.setWar("src/main/webapp");
		context.setSessionHandler(new SessionHandler());
		context.addEventListener(guiceContextListener);
		
		context.addFilter(GuiceFilter.class, "/*", EnumSet.allOf(DispatcherType.class));
		
		ResourceCollection resources = new ResourceCollection(new String[] { "src/main/ui/app" });

		context.setBaseResource(resources);
		context.setInitParameter("cacheControl", "no-store,no-cache,must-revalidate");
		context.setWelcomeFiles(new String[] { "index.html" });

		SessionHandler sessionHandler = new SessionHandler();
		sessionHandler.getSessionManager().setMaxInactiveInterval(60 * 60 * 1000);
		sessionHandler.setHandler(context);
		server.setHandler(sessionHandler);		
		
		return server;		
	}

}
