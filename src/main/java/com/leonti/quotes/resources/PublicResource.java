package com.leonti.quotes.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.StreamingOutput;

import com.leonti.quotes.model.Quote;

@Path("public/widget")
public interface PublicResource {
	
	@GET
	@Path("{id}/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Quote getWidgetQuoteAsJson(@PathParam("id") Long widgetId);

	@GET
	@Path("{id}/image/w/{width}/h/{height}")	
	@Produces("image/png")
	public StreamingOutput getWidgetQuoteAsImage(
			@PathParam("id") Long widgetId, @PathParam("width") int width,  @PathParam("height") int height);
	
	@GET
	@Path("{id}/iframe/css/{css}")	
	@Produces("text/html")
	public String getWidgetQuoteAsIframe(
			@PathParam("id") Long widgetId, @PathParam("css") String css);	
}
