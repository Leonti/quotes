package com.leonti.quotes.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.leonti.quotes.model.Quote;

@Path("quote")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface QuoteResource {

	Log log = LogFactory.getLog(QuoteResource.class);
	
	@POST
	public Quote create(Quote quote);
	
	@GET
	public List<Quote> getQuotesForUser();		

	@GET
	@Path("tags")
	public List<String> getTagsForUser();	
	
	@PUT
	@Path("{id}")
	public Quote update(@PathParam("id") long id, Quote quote);

	@GET
	@Path("{id}")
	public Quote getQuote(@PathParam("id") long id);	

	@DELETE
	@Consumes(MediaType.TEXT_PLAIN)
	@Path("{id}")
	public void removeQuote(@PathParam("id") long id);	
}
