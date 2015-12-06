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
		"FROM LITTER, LITTER_COLLECTION WHERE LITTER_COLLECTION.VOL_ID=1 AND LITTER_COLLECTION.LITTER_ID=LITTER.LITTER_ID;";
			
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
	
	@Override
	public List<LitterCollection> getLitterCollectionList(){
		
		buildLitterCollectionList();
		return litterCollectionList;
	}
	
}
