package com.mattbozelka.repository;

import java.util.ArrayList;
import java.util.List;

import com.mattbozelka.model.*;

public class LoginStub implements LoginRepository {
	
	private List<User> userList;
	
	public LoginStub() {
		userList = new ArrayList<User>();
	}
	
	@Override
	public List<User> getUserList(){
		
		User u1 = new User("testUser");
		
		userList.add(u1);
		
		return userList;
	}
}
