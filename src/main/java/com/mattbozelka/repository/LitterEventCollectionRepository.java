package com.mattbozelka.repository;

import java.util.List;

import com.mattbozelka.model.LitterEventCollection;

public interface LitterEventCollectionRepository {

	List<LitterEventCollection> getLitterEventCollectionList();

}