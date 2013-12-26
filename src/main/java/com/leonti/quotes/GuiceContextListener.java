package com.leonti.quotes;

import java.util.LinkedList;

import javax.servlet.ServletContextEvent;

import org.apache.shiro.guice.web.ShiroWebModule;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.servlet.GuiceServletContextListener;
import com.leonti.quotes.modules.PersistenceModule;
import com.leonti.quotes.modules.ResourceModule;
import com.leonti.quotes.modules.ServiceModule;
import com.leonti.quotes.modules.ShiroModule;

public class GuiceContextListener extends GuiceServletContextListener {

	private Injector injector;
	
    @Override
    public void contextInitialized(final ServletContextEvent servletContextEvent) {

            this.injector = Guice.createInjector(getModules(servletContextEvent));
            super.contextInitialized(servletContextEvent);
    }
	
    protected LinkedList<Module> getModules(ServletContextEvent servletContextEvent) {
    	LinkedList<Module> modules = new LinkedList<>();
    	
    	modules.add(new ConfigurationModule());
    	modules.add(new ShiroModule(servletContextEvent.getServletContext()));
    	modules.add(ShiroWebModule.guiceFilterModule());
    	modules.add(new ResourceModule());
    	modules.add(new ServiceModule());
    	modules.add(new PersistenceModule());
    	
    	return modules;
    }
    
	@Override
	protected Injector getInjector() {
		return injector;
	}

}