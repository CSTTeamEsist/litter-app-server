package com.mattbozelka.repository;

import java.util.ArrayList;
import java.util.List;

import com.mattbozelka.model.*;

public class LitterCollectionListStub implements LitterCollectionRepository {
	
	private List<LitterCollection> litterCollectionList;
	
	public LitterCollectionListStub() {
		litterCollectionList = new ArrayList<LitterCollection>();
	}
	
	
	@Override
	public List<LitterCollection> getLitterCollectionList(){
		LitterCollection lc1 = new LitterCollection (111, 222, "Name222", "Icon222", 333, 444, 7);
		LitterCollection lc2 = new LitterCollection (222, 333, "Name333", "Icon333", 444, 555, 3);
		LitterCollection lc3 = new LitterCollection (333, 444, "Name444", "Icon444", 555, 666, 4);
		
		litterCollectionList.add(lc1);
		litterCollectionList.add(lc2);
		litterCollectionList.add(lc3);
		
		return litterCollectionList;
	}
	
}
