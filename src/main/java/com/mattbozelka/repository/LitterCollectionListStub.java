package com.mattbozelka.repository;

import java.util.ArrayList;
import java.util.List;

import com.mattbozelka.model.*;

public class LitterCollectionListStub implements LitterCollectionRepository {
	
	private List<LitterCollection> litterCollectionList;
	
	public LitterCollectionListStub() {
		litterCollectionList = new ArrayList<LitterCollection>();
	}
	
	private void buildLitterCollectionList(){

		String sql = "SELECT LITTER_COLLECTION.EVENT_ID, LITTER_COLLECTION.LITTER_ID, LITTER.NAME, LITTER.PICTURE_URL, " +
		"LITTER_COLLECTION.VOL_ID, LITTER_COLLECTION.TEAM_ID, LITTER_COLLECTION.TALLY " + 
		"FROM LITTER, LITTER_COLLECTION WHERE LITTER_COLLECTION.LITTER_ID=LITTER.LITTER_ID;";
		buildList(sql);
	}
	
	private void buildLitterCollectionListByUserAndEvent(String userId,String eventId){

		String sql = "SELECT LITTER_COLLECTION.EVENT_ID, LITTER_COLLECTION.LITTER_ID, LITTER.NAME, LITTER.PICTURE_URL, " +
		"LITTER_COLLECTION.VOL_ID, LITTER_COLLECTION.TEAM_ID, LITTER_COLLECTION.TALLY " + 
		"FROM LITTER, LITTER_COLLECTION WHERE LITTER_COLLECTION.LITTER_ID=LITTER.LITTER_ID " +
		"AND LITTER_COLLECTION.VOL_ID='" + userId + "' AND LITTER_COLLECTION.EVENT_ID='" + eventId + "' ;";
		buildList(sql);
	}
	
	//builds a list of LitterCollections fromt he database from 
	//passed sql query.
	private void buildList(String sql){
		DatabaseConnection dbcon = new DatabaseConnection();
		ArrayList<String[]> queryResults = dbcon.getQueryResults(sql);
		for (String[] row : queryResults){
			
			Long eventID = dbcon.handleLongNulls(row[0]);
			Long litterID = dbcon.handleLongNulls(row[1]);
			String litterName = dbcon.handleStrNulls(row[2]);
			String litterIcon = dbcon.handleStrNulls(row[3]);
			Long volID = dbcon.handleLongNulls(row[4]);
			Long teamID = dbcon.handleLongNulls(row[5]);
			Long tally = dbcon.handleLongNulls(row[6]);
			
			LitterCollection lc = 
					new LitterCollection (eventID, litterID, litterName, litterIcon, volID, teamID, tally);
			
			litterCollectionList.add(lc);
		
		}
	}

	
	//builds a list of LitterCollections that have not bee collected
	//from the database for a specific user and event.
	private void buildUncollectedLitterList(String userId,String eventId){
		
		String sql=
		"SELECT t1.* " +
		"FROM LITTER t1 " +
		"LEFT JOIN LITTER_COLLECTION t2 ON  " +
		"t2.LITTER_ID = t1.LITTER_ID AND t2.VOL_ID='" + userId + "' AND t2.EVENT_ID='" + eventId + "' " +
		"WHERE t2.LITTER_ID IS NULL ";
		
		DatabaseConnection dbcon = new DatabaseConnection();
		ArrayList<String[]> queryResults = dbcon.getQueryResults(sql);
		for (String[] row : queryResults){
			
			Long eventID = dbcon.handleLongNulls(eventId);
			Long litterID = dbcon.handleLongNulls(row[0]);
			String litterName = dbcon.handleStrNulls(row[1]);
			String litterIcon = dbcon.handleStrNulls(row[2]);
			Long volID = dbcon.handleLongNulls(userId);
			Long teamID = dbcon.handleLongNulls("1");
			Long tally = dbcon.handleLongNulls(""); //default to zero
			
			LitterCollection lc = 
					new LitterCollection (eventID, litterID, litterName, litterIcon, volID, teamID, tally);
			
			litterCollectionList.add(lc);
		
		}
	}

	@Override
	public List<LitterCollection> updateEvent(Long litterID, Long volID, Long teamID, Long eventID, Long tally){

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

		//if record exists, update it, otherwise insert a new record.
		if (dbcon.recordExists(selectSQL) ){
			dbcon.updateTable(updateSQL);
			//System.out.println("update");
		} else  {
			dbcon.updateTable(insertSQL);
			//System.out.println("insert");
		}

		buildLitterCollectionList();
		
		return litterCollectionList;
		
	}
	
	@Override
	public List<LitterCollection> getLitterCollectionList(){
		buildLitterCollectionList();
		return litterCollectionList;
	}
	
	@Override
	public List<LitterCollection> getLitterCollectionListByUserAndEvent(String userId,String eventId){
		buildLitterCollectionListByUserAndEvent(userId,eventId);
		buildUncollectedLitterList(userId,eventId);
		return litterCollectionList;
	}
	
}
