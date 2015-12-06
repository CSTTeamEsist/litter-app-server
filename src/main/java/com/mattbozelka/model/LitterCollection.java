package com.mattbozelka.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class LitterCollection {
	
	private long eventID;
	private String litterName;
	private String litterIcon;
	private long litterID;
	private long volID;
	private long teamID;
	private long tally;
	
	
	public LitterCollection(){
		this.eventID = -1;
        this.litterID = -1;
        this.setLitterName("");
        this.setLitterIcon("");
        this.volID = -1;
        this.teamID = -1;
        this.tally = -1;
	}
	
	public LitterCollection(long eventID, long litterID, String litterName,
			String litterIcon, long volID, long teamID, long tally){
		this.eventID = eventID;
        this.litterID = litterID;
		this.setLitterName(litterName);
		this.setLitterIcon(litterIcon);
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

	public String getLitterName() {
		return litterName;
	}

	public void setLitterName(String litterName) {
		this.litterName = litterName;
	}

	public String getLitterIcon() {
		return litterIcon;
	}

	public void setLitterIcon(String litterIcon) {
		this.litterIcon = litterIcon;
	}
}
