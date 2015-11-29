package com.mattbozelka;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.mattbozelka.model.User;
import com.mattbozelka.repository.LoginRepository;
import com.mattbozelka.repository.LoginStub;

/**
 * Root resource (exposed at "login/test@email.com/myPass123" path)
 */
@Path("login/test@email.com/myPass123")
public class LoginResource {
	
   LoginRepository userList = new LoginStub();
	
   @GET
   @Produces(MediaType.APPLICATION_JSON)
   public List<User> getIt() {
       return userList.getUserList();
   }
}
