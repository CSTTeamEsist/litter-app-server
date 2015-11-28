package com.mattbozelka.repository;

import java.util.List;

import com.mattbozelka.model.Event;

public interface EventListRepository {

	List<Event> getEventList();

}