package com.mattbozelka.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class LitterEventCollection {
	
	private long eventID;
	private long litterID;
	private long volID;
	private long teamID;
	private long tally;
	
	
	public LitterEventCollection(){
		this.eventID = -1;
        this.litterID = -1;
        this.volID = -1;
        this.teamID = -1;
        this.tally = -1;
	}
	
	public LitterEventCollection(long eventID, long litterID, long volID, long teamID, long tally){
		this.eventID = eventID;
        this.litterID = litterID;
        this.volID = volID;
        this.teamID = teamID;
        this.tally = tally;
	}
	
	public long getEventID() {
		return eventID;
	}

	public long getLitterID() {
		return litterID;
	}

	public long getVolID() {
		return volID;
	}

	public long getTeamID() {
		return teamID;
	}

	public long getTally() {
		return tally;
	}

	public void setTally(long tally) {
		this.tally = tally;
	}


}
