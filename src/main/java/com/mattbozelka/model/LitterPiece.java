package com.mattbozelka.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class LitterPiece {
	//LitterPiece class stores one litter item
	
	private long litterID;
	private String name;
	private long count;
	private String iconName;
	
	public LitterPiece(){
		this.litterID = 0;
		this.name = null;
        this.count = 0;
        this.iconName = null;
	}
	
	public LitterPiece(long litterID, String name, String iconName, long count) {
		this.litterID = litterID;
		this.name = name;
		this.count = count;
		this.iconName = iconName;
	}

	@XmlElement
	public Long getLitterID() {
		return litterID;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public String getIconName() {
		return iconName;
	}

	public void setIconName(String iconName) {
		this.iconName = iconName;
	}
	
}
