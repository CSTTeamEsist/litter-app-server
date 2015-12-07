package com.mattbozelka.repository;

import java.util.List;

import com.mattbozelka.model.LitterEventCollection;

public interface LitterEventCollectionRepository {

	List<LitterEventCollection> getLitterEventCollectionList();
    List<LitterEventCollection> updateEvent(Long litterID, Long volID, Long teamID, Long eventID, Long tally);
}