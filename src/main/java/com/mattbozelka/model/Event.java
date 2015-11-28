package com.mattbozelka.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Event {
	
	private long eventID;
	private String location;
	private String eventDate;
	private long orgID;
	
	
	public Event(){
		this.eventID = -1;
        this.location = null;
        this.eventDate = null;
        this.orgID = -1;
	}
	
	public Event(long eventID, String location, String eventDate, long orgID) {
		this.eventID = eventID;
		this.location = location;
		this.eventDate = eventDate;
		this.orgID = orgID;
	}
	
	public long getEventID() {
		return eventID;
	}

	public void setEventID(long eventID) {
		this.eventID = eventID;
	}

	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}

	public String getEventDate() {
		return eventDate;
	}

	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}

	public long getOrgID() {
		return orgID;
	}
		
	public void orgID(long orgID) {
		this.orgID = orgID;
		
	}
}
