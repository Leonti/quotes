package com.leonti.quotes.resources;

import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.leonti.quotes.model.User;

@Path("rest/user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface UserResource {

	Log log = LogFactory.getLog(UserResource.class);
	
	@GET
	public User get();
	
	@POST
	@Path("login")
	public User login(Map<String, String> data);	
	
	@GET
	@Path("logout")
	public void logout();
	
}
