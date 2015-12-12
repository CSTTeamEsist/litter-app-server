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
//@Path("login/test@email.com/myPass123")

@Path("login")
public class LoginResource {
	
   LoginRepository userList = new LoginStub();
	
   @GET
   @Path("/")
   @Produces(MediaType.APPLICATION_JSON)
   public List<User> getIt() {
       return userList.getUserList();
   }
   

   @GET
   @Path("/{email}/{password}/")
   @Produces(MediaType.APPLICATION_JSON)
   public User getUser(@PathParam("email") String email, @PathParam("password") String password) {
       return userList.getUserID(email,password);
   }
   
   
}
