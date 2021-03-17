package com.register.system.model;

import com.fasterxml.jackson.annotation.JsonProperty;


public class GuestResponse {
		
	@JsonProperty("_embedded")
	private Guests embedded;
	
	GuestResponse(){
		
	}

	public Guests getEmbedded() {
		return embedded;
	}

	public void setEmbedded(Guests embedded) {
		this.embedded = embedded;
	}

	
}
