package com.mattbozelka.repository;

import java.util.ArrayList;
import java.util.List;

import com.mattbozelka.model.*;

public class LitterListStub implements LitterListRepository {
	
	private List<LitterPiece> litterList;
	
	public LitterListStub() {
		
		litterList = new ArrayList<LitterPiece>();
	}
	
	
	@Override
	public List<LitterPiece> getLitterList(){
		
		LitterPiece l1 = new LitterPiece("Plastic", 0, "icon1");
		LitterPiece l2 = new LitterPiece("Rubber", 0, "icon2");
		LitterPiece l3 = new LitterPiece("Metal", 0, "icon3");
		
		litterList.add(l1);
		litterList.add(l2);
		litterList.add(l3);
		
		return litterList;
	}
	
}
