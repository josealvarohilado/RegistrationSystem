package com.register.system.model;

import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class EventRegistration {

	@EmbeddedId
	private RegistrationId pk;
	private Date timeRegistered;
	
	public RegistrationId getPk() {
		return pk;
	}
	public void setPk(RegistrationId pk) {
		this.pk = pk;
	}
	public Date getTimeRegistered() {
		return timeRegistered;
	}
	public void setTimeRegistered(Date timeRegistered) {
		this.timeRegistered = timeRegistered;
	}
}
