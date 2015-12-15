package com.mattbozelka.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class LitterCollection {
	//LitterCollection class stores list of litter items
	//and a tally of times it was collected.
	
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
	
	@XmlElement
	public long getEventID() {
		return eventID;
	}
	
	@XmlElement
	public long getLitterID() {
		return litterID;
	}
	
	@XmlElement
	public long getVolID() {
		return volID;
	}
	
	@XmlElement
	public long getTeamID() {
		return teamID;
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
