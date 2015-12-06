package com.mattbozelka.repository;

import java.util.List;

import com.mattbozelka.model.Volunteer;

public interface VolunteerListRepository {

	List<Volunteer> getVolunteerList();
	List<Volunteer>  addVolunteer(String fName, String lName, String email, String password);
}