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
	}
	
	@Override
	public List<Volunteer> getVolunteerList(){
		/*
		Volunteer v1 = new Volunteer (123, "Joe", "Bruin", "jbruin@gmail.com", "stuffing");
		Volunteer v2 = new Volunteer (324, "Tommy", "Trojan", "ttrojan@gmail.com", "turkey");
		Volunteer v3 = new Volunteer (756, "Monte", "Rey", "mrey@gmail.com", "mashed potatoes");
		
		volunteerList.add(v1);
		volunteerList.add(v2);
		volunteerList.add(v3);*/
		buildVolunteerList();
		return volunteerList;
	}
	
}
