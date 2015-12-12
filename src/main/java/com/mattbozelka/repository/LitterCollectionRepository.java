package com.mattbozelka.repository;

import java.util.List;

import com.mattbozelka.model.LitterCollection;

public interface LitterCollectionRepository {

	List<LitterCollection> getLitterCollectionList();
	List<LitterCollection> getLitterCollectionListByUserAndEvent(String userId,String eventId);
    List<LitterCollection> updateEvent(Long litterID, Long volID, Long teamID, Long eventID, Long tally);

}