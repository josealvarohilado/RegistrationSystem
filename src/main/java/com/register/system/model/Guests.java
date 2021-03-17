package com.register.system.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Guests {

	@JsonProperty("content")
	List<Guest> guests;
	
	
	public Guests(){
		
	}

	public List<Guest> getGuests() {
		return guests;
	}

	public void setGuests(List<Guest> guests) {
		this.guests = guests;
	}
}