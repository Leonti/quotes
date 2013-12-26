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

import com.leonti.quotes.model.Widget;

@Path("widget")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface WidgetResource {

	@POST
	public Widget create(Widget widget);
	
	@GET
	public List<Widget> getWidgetsForUser();	

	@GET
	@Path("{id}")
	public Widget getWidget(@PathParam("id") long id);	

	@DELETE
	@Path("{id}")
	public void removeWidget(@PathParam("id") long id);		
	
	@PUT
	@Path("{id}")
	public Widget update(@PathParam("id") long id, Widget widget);
	
}
