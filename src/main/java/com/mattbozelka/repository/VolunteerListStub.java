package com.mattbozelka.repository;

import java.util.ArrayList;
import java.util.List;

import com.mattbozelka.model.*;

public class VolunteerListStub implements VolunteerListRepository {
	
	private List<Volunteer> volunteerList;
	
	public VolunteerListStub() {
		volunteerList = new ArrayList<Volunteer>();
	}
	
	private void buildVolunteerList(){
		
		DatabaseConnection dbcon = new DatabaseConnection();
		ArrayList<String[]> queryResults = dbcon.getQueryResults("Select * From VOLUNTEER");
		for (String[] row : queryResults){

			Long volID = dbcon.handleLongNulls(row[0]);
			String fName = dbcon.handleStrNulls(row[1]);
			String lName = dbcon.handleStrNulls(row[2]);
			String email = dbcon.handleStrNulls(row[3]);
			String password = dbcon.handleStrNulls(row[4]);
			
			Volunteer v = new Volunteer (volID, fName, lName, email, password);
			
			volunteerList.add(v);
		
		}

		//dbcon.closeConnection();
	}

	@Override
	public List<Volunteer>  addVolunteer(String fName, String lName, String email, String password){

		DatabaseConnection dbcon = new DatabaseConnection();
		String sql = "INSERT INTO VOLUNTEER (`F_NAME`, `L_NAME`, `EMAIL_ADDRESS`, `PASSWORD`) " +
		"VALUES ('" + fName + "', '" + lName + "', '" + email + "', '" + password + "');";
		
		if (dbcon.updateTable(sql)){
			//System.out.println("update");
		} else {
			//System.out.println("no update");
		}
		
		buildVolunteerList();
		//dbcon.closeConnection();
		return volunteerList;
		
	}
	
	@Override
	public List<Volunteer> getVolunteerList(){
		
		buildVolunteerList();
		return volunteerList;
	}
	
	
}
