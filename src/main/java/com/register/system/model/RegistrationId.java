package com.register.system.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class RegistrationId implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	private Guest guest;
	
	@ManyToOne
	private Event event;

	public Guest getGuest() {
		return guest;
	}

	public void setGuest(Guest guest) {
		this.guest = guest;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}
}
