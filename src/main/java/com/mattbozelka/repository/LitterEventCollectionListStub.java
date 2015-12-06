package com.mattbozelka.repository;

import java.util.ArrayList;
import java.util.List;

import com.mattbozelka.model.*;

public class LitterEventCollectionListStub implements LitterEventCollectionRepository {
	
	private List<LitterEventCollection> litterEventCollectionList;
	
	public LitterEventCollectionListStub() {
		litterEventCollectionList = new ArrayList<LitterEventCollection>();
	}
	
	private void buildLitterEventCollectionList(){
		
		DatabaseConnection dbcon = new DatabaseConnection();
		ArrayList<String[]> queryResults = dbcon.getQueryResults("Select * From LITTER_COLLECTION");
		for (String[] row : queryResults){
			
			Long litterID = dbcon.handleLongNulls(row[0]);
			Long volID = dbcon.handleLongNulls(row[1]);
			Long teamID = dbcon.handleLongNulls(row[2]);
			Long eventID = dbcon.handleLongNulls(row[3]);
			Long tally = dbcon.handleLongNulls(row[4]);
			
			LitterEventCollection lec = new 
					LitterEventCollection (litterID, volID, teamID, eventID, tally);
			
			litterEventCollectionList.add(lec);
		
		}
	}
	
	@Override
	public List<LitterEventCollection> getLitterEventCollectionList(){
		
		buildLitterEventCollectionList();
		
		return litterEventCollectionList;
	}
	
}
