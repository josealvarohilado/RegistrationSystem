package com.register.system.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity	
public class Event {
	
	@Id
	@GeneratedValue
	private int eid;
	private String eventName;
	private String eventDescription;
	private Date eventDate;
	
	@OneToOne
	@JoinColumn(referencedColumnName="id")
	private Locations location;
	
	public int getEid() {
		return eid;
	}
	public void setEid(int eid) {
		this.eid = eid;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getEventDescription() {
		return eventDescription;
	}
	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}
	public Locations getLocation() {
		return location;
	}
	public void setLocation(Locations location) {
		this.location = location;
	}
	public Date getEventDate() {
		return eventDate;
	}
	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}
	@Override
	public String toString() {
		return "Event [eid=" + eid + ", eventName=" + eventName + ", eventDescription=" + eventDescription
				+ ", eventDate=" + eventDate + ", location=" + location + "]";
	}
	
	
}
