package com.mattbozelka.repository;

import java.util.ArrayList;
import java.util.List;

import com.mattbozelka.model.*;

public class LitterListStub implements LitterListRepository {
	
	private List<LitterPiece> litterList;
	
	public LitterListStub() {
		
		litterList = new ArrayList<LitterPiece>();
	}
	

	private void buildLitterList(){
		
		DatabaseConnection dbcon = new DatabaseConnection();
		ArrayList<String[]> queryResults = dbcon.getQueryResults("Select * From LITTER");
		for (String[] row : queryResults){
			
			Long litterID = dbcon.handleLongNulls(row[0]);
			String litterName = dbcon.handleStrNulls(row[1]);
			String litterIcon = dbcon.handleStrNulls(row[2]);
			
			//TODO: LitterPiece.count is set to zero until determining its purpose
			LitterPiece lp = new LitterPiece (litterID, litterName, litterIcon, 0);
			
			litterList.add(lp);
		
		}
	}
	
	@Override
	public List<LitterPiece> getLitterList(){
		buildLitterList();
		
		return litterList;
	}
	
}
