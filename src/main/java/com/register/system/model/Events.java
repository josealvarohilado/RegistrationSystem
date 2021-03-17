package com.register.system.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Events {
	
	@JsonProperty("content")
	private List<Event> events;

	Events (){
		
	}
	
	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}
	
	
}
