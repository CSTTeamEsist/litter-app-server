package com.mattbozelka.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User {
	
	private String userID;
	
	public User(){
		this.userID = "";
	}
	
	public User(String userID) {
		this.setUserID(userID);
	}
	
	private void setUserID(String userID) {
		this.userID = userID;
		
	}

	public String getUserID() {
		return userID;
	}

}
