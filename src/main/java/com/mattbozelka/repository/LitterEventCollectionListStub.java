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
	public List<LitterEventCollection> updateEvent(Long litterID, Long volID, Long teamID, Long eventID, Long tally){

		DatabaseConnection dbcon = new DatabaseConnection();
		
		String selectSQL = "SELECT * FROM `LITTER_COLLECTION` WHERE " +
				" `LITTER_ID`=" + litterID + " " +
				"and `VOL_ID`=" + volID + " and `EVENT_ID`=" + eventID + ";";
				
		String updateSQL = "UPDATE `LITTER_COLLECTION` SET " +
			"`TALLY`='" + tally + "' WHERE `LITTER_ID`='" + litterID + "' " +
			"and `VOL_ID`='" + volID + "' and `EVENT_ID`='" + eventID + "';";
		
		String insertSQL = "INSERT INTO `LITTER_COLLECTION` " +
			"(`LITTER_ID`, `VOL_ID`, `TEAM_ID`, `EVENT_ID`, `TALLY`)" +
			"VALUES ('" + litterID + "', '" + volID + "', '" + teamID + 
			"', '" + eventID + "', '" + tally + "');";

		if (dbcon.recordExists(selectSQL) ){
			dbcon.updateTable(updateSQL);
			System.out.println("update");
		} else  {
			dbcon.updateTable(insertSQL);
			System.out.println("insert");
		}

		buildLitterEventCollectionList();
		
		return litterEventCollectionList;
		
	}
	
	
	@Override
	public List<LitterEventCollection> getLitterEventCollectionList(){
		
		buildLitterEventCollectionList();
		
		return litterEventCollectionList;
	}
	
}
