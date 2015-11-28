package com.mattbozelka.repository;

import java.util.List;

import com.mattbozelka.model.Volunteer;

public interface VolunteerListRepository {

	List<Volunteer> getVolunteerList();

}