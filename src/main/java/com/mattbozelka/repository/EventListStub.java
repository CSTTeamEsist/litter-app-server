package com.mattbozelka.repository;

import java.util.ArrayList;
import java.util.List;

import com.mattbozelka.model.*;

public class EventListStub implements EventListRepository {
	
	private List<Event> eventList;
	
	public EventListStub() {
		eventList = new ArrayList<Event>();
	}

	private void buildEventList(){
		
		DatabaseConnection dbcon = new DatabaseConnection();
		ArrayList<String[]> queryResults = dbcon.getQueryResults("Select * From EVENT");
		for (String[] row : queryResults){
			
			Long eventID = dbcon.handleLongNulls(row[0]);
			String location = dbcon.handleStrNulls(row[1]);
			String eventDate = dbcon.handleStrNulls(row[2]);
			Long orgID = dbcon.handleLongNulls(row[3]);
			
			Event ev = new Event (eventID, location, eventDate, orgID);
			
			eventList.add(ev);
		
		}
	}
	
	@Override
	public List<Event> getEventList(){
		
		buildEventList();
		return eventList;
	}
	
}
