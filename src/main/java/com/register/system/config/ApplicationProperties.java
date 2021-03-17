package com.register.system.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("app.properties")
public class ApplicationProperties {

	private final String guestServiceUrl = "http://guest-service/guests/";
	private final String eventServiceUrl = "http://event-service/events/";

	public String getGuestServiceUrl() {
		return guestServiceUrl;
	}

	public String getGuestSearchUrl(String guestName) {
		return guestServiceUrl + "search/findByNameWildcard?guestName=" + guestName;
	}

	public String getGuest(int aid) {
		return guestServiceUrl + aid;
	}
	
	public String getEventServiceUrl() {
		return eventServiceUrl;
	}
	
	public String getEventSearchUrl(String eventName) {
		return eventServiceUrl + "search/findByEventnameWildcard?eventName=" + eventName;
	}
	
	public String getEvent(int eid) {
		return eventServiceUrl + eid;
	}
	
}
