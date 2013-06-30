package com.leonti.quotes.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Path("quote")
public class QuoteResource {

	Log log = LogFactory.getLog(QuoteResource.class);
	
	@GET
	public String test() {
		
		log.info("Receiving test request");
		
		return "Hello";
	}
}
