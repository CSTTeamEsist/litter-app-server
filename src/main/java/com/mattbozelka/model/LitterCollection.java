package com.mattbozelka.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class LitterCollection {
	
	private long eventID;
	private long litterID;
	private long volID;
	private long teamID;
	private long tally;
	
	
	public LitterCollection(){
		this.eventID = -1;
        this.litterID = -1;
        this.volID = -1;
        this.teamID = -1;
        this.tally = -1;
	}
	
	public LitterCollection(long eventID, long litterID, long volID, long teamID, long tally){
		this.eventID = eventID;
        this.litterID = litterID;
        this.volID = volID;
        this.teamID = teamID;
        this.tally = tally;
	}
	
	public long getEventID() {
		return eventID;
	}

	public void setEventID(long eventID) {
		this.eventID = eventID;
	}

	public long getLitterID() {
		return litterID;
	}

	public void setLitterID(long litterID) {
		this.litterID = litterID;
	}
	
	public long getVolID() {
		return volID;
	}

	public void setVolID(long volID) {
		this.volID = volID;
	}

	public long getTeamID() {
		return teamID;
	}

	public void setTeamID(long teamID) {
		this.teamID = teamID;
	}

	public long getTally() {
		return tally;
	}

	public void setTally(long tally) {
		this.tally = tally;
	}
}
