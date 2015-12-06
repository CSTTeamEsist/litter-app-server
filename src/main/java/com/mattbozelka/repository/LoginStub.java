package com.mattbozelka.repository;

import java.util.ArrayList;
import java.util.List;

import com.mattbozelka.model.*;

public class LoginStub implements LoginRepository {
	
	private List<User> userList;
	
	public LoginStub() {
		userList = new ArrayList<User>();
	}

	private void buildUserList(){
		
		DatabaseConnection dbcon = new DatabaseConnection();
		ArrayList<String[]> queryResults = dbcon.getQueryResults("Select EMAIL_ADDRESS From VOLUNTEER");
		for (String[] row : queryResults){
			
			String email = dbcon.handleStrNulls(row[0]);
			
			User u = new User (email);
			
			userList.add(u);
		
		}
	}
	
	@Override
	public List<User> getUserList(){
		
		buildUserList();
		return userList;
	}
}
