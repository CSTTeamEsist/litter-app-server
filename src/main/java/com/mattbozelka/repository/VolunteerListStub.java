package com.mattbozelka.repository;

import java.util.ArrayList;
import java.util.List;

import com.mattbozelka.model.*;

public class VolunteerListStub implements VolunteerListRepository {
	
	private List<Volunteer> volunteerList;
	
	public VolunteerListStub() {
		volunteerList = new ArrayList<Volunteer>();
	}
	
	
	@Override
	public List<Volunteer> getVolunteerList(){
		
		Volunteer v1 = new Volunteer (123, "Joe", "Bruin", "jbruin@gmail.com", "stuffing");
		Volunteer v2 = new Volunteer (324, "Tommy", "Trojan", "ttrojan@gmail.com", "turkey");
		Volunteer v3 = new Volunteer (756, "Monte", "Rey", "mrey@gmail.com", "mashed potatoes");
		
		volunteerList.add(v1);
		volunteerList.add(v2);
		volunteerList.add(v3);
		
		return volunteerList;
	}
	
}
