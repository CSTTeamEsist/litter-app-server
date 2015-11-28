package com.mattbozelka.repository;

import java.util.ArrayList;
import java.util.List;

import com.mattbozelka.model.*;

public class EventListStub implements EventListRepository {
	
	private List<Event> eventList;
	
	public EventListStub() {
		eventList = new ArrayList<Event>();
	}
	
	
	@Override
	public List<Event> getEventList(){
		
		Event e1 = new Event (111, "Santa Monica", "December 3, 2015", 10);
		Event e2 = new Event (112, "Venice Beach", "November 20, 2015", 23);
		Event e3 = new Event (213, "Salton Sea", "January 4, 2016", 13);
		
		eventList.add(e1);
		eventList.add(e2);
		eventList.add(e3);
		
		return eventList;
	}
	
}
