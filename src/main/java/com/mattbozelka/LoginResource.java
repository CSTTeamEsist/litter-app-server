package com.mattbozelka;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.mattbozelka.model.User;
import com.mattbozelka.repository.LoginRepository;
import com.mattbozelka.repository.LoginStub;

/**
 * Root resource (exposed at "login/test@email.com/myPass123" path)
 */

//REQ1, REQ13

@Path("login")
public class LoginResource {
	
   LoginRepository userList = new LoginStub();
   
   //returns a list of user ids
   @GET
   @Path("/")
   @Produces(MediaType.APPLICATION_JSON)
   public List<User> getIt() {
       return userList.getUserList();
   }
   
   //returns a userid if user exists in database
   //or empty if not
   //validated by email address and password
   @GET
   @Path("/{email}/{password}/")
   @Produces(MediaType.APPLICATION_JSON)
   public User getUser(@PathParam("email") String email, @PathParam("password") String password) {
       return userList.getUserID(email,password);
   }
   
   
}
