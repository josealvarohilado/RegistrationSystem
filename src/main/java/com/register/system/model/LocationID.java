package com.register.system.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Embeddable
public class LocationID implements Serializable{

	private static final long serialVersionUID = 1L;
	@OneToOne
	private Locations location;
	
	@OneToOne
	@JoinColumn(referencedColumnName="eid")
	private Event event;

	public Locations getLocation() {
		return location;
	}

	public void setLocation(Locations location) {
		this.location = location;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}
}
