package com.register.system.web;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.register.system.config.ApplicationProperties;
import com.register.system.model.Event;
import com.register.system.model.EventRegistration;
import com.register.system.model.Guest;
import com.register.system.model.Guests;
import com.register.system.model.RegistrationId;
import com.register.system.repositories.EventRegistrationRepo;
import com.register.system.repositories.EventRepo;
import com.register.system.repositories.GuestRepo;

@Controller
@EnableConfigurationProperties(ApplicationProperties.class)
public class RegSystemController {
	
	@Autowired
	ApplicationProperties appProperties;

	@Autowired
	GuestRepo guestRepo;
	
	@Autowired
	EventRepo eventRepo;
	
	@Autowired
	EventRegistrationRepo eventRegRepo;
	
	@Autowired
	RestTemplate restTemplate;
	
	@RequestMapping("/")
	public String home() {
		return "home";
	}
	
	@RequestMapping("/newGuest")
	public String newGuest() {
		return "NewGuest";
	}
	
	@RequestMapping("/createGuest")
	public ModelAndView createGuest(Guest guest) {
		// guestRepo.save(guest);
		Guest newGuest = restTemplate.postForObject(appProperties.getGuestServiceUrl(), guest, Guest.class);
		ModelAndView mv = new ModelAndView("NewGuest");
		String confirmation = "";
		if (newGuest != null) confirmation = "<p>Guest Registration Saved!</p>";
		mv.addObject("confirmation",confirmation);
		return mv;
	}
	
	@RequestMapping("/searchGuest")
	public ModelAndView guestSearch(@RequestParam String guestName) {
		ModelAndView mv = new ModelAndView("home");
		if(guestName.isBlank()) guestName = null;
		Guests searchResult = restTemplate.getForObject(appProperties.getGuestSearchUrl(guestName), Guests.class);
		mv.addObject("guestSearch",searchResult.getGuests());
		return mv;
	}
	
	@RequestMapping("/guest")
	public ModelAndView guest(@RequestParam int aid) {
		ModelAndView mv = new ModelAndView("showGuest");
		Guest guestDetails = restTemplate.getForObject(appProperties.getGuest(aid), Guest.class);
		mv.addObject("events",eventRepo.findAll());
		mv.addObject("guestDetails",guestDetails);
		try {
			mv.addObject("registeredEvents",eventRegRepo.findByPkGuestAid(aid));
		} 
		catch(Exception e) {
			System.out.println(e.toString());
		}
		return mv;
	}
	
	@RequestMapping("/registerGuest")
	public String registerGuest(@RequestParam int aid,  @RequestParam String eid) {
		EventRegistration eventRegistration = new EventRegistration();
		Date dt = new Date();
		// Guest guest = guestRepo.findById(Integer.parseInt(aid)).orElse(new Guest());
		Guest guest = restTemplate.getForObject(appProperties.getGuest(aid), Guest.class);
		Event event = eventRepo.findById(Integer.parseInt(eid)).orElse(new Event());
		eventRegistration.setPk(new RegistrationId());
		eventRegistration.getPk().setEvent(event);
		eventRegistration.getPk().setGuest(guest);
		eventRegistration.setTimeRegistered(dt);
		if(eventRegRepo.findById(eventRegistration.getPk()).isEmpty()) {
			System.out.println("Guest not registered to event");
			eventRegRepo.save(eventRegistration);
		}
		else System.out.println("Guest registered to event");
		return "redirect:guest?aid=" + aid;
	}
	
	@RequestMapping("/updateGuest")
	public ModelAndView updateGuest(Guest guest) {
		ModelAndView mv = new ModelAndView("showGuest");
		String updateGuestConfirmation = "";
		
		Guest checkGuest = restTemplate.getForObject(appProperties.getGuest(guest.getAid()), Guest.class);
		if(checkGuest != null) {
			// restTemplate.put("http://localhost:8071/guests/" + guest.getAid(), guest);
			HttpEntity<Guest> request = new HttpEntity<>(new Guest());
			ResponseEntity<Guest> response = restTemplate.exchange(appProperties.getGuest(guest.getAid()), HttpMethod.PUT, request,Guest.class);
			Guest updatedGuest = guest;
			updatedGuest.setAid(response.getBody().getAid());
			restTemplate.execute(appProperties.getGuest(guest.getAid()), HttpMethod.PUT, requestCallback(updatedGuest), clientHttpResponse -> null);
			if (updatedGuest != null) updateGuestConfirmation = "Guest Details Updated!!!";
			mv.addObject("guestDetails",updatedGuest);
			mv.addObject("events",eventRepo.findAll());
			mv.addObject("registeredEvents",eventRegRepo.findByPkGuestAid(updatedGuest.getAid()));
		} else {
			updateGuestConfirmation = "Guest Not Found";
		}
		mv.addObject("updateGuestConfirmation",updateGuestConfirmation);
		return mv;
	}

	private RequestCallback requestCallback(Guest updatedGuest) {
		// TODO Auto-generated method stub
		return clientHttpRequest -> {
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(clientHttpRequest.getBody(), updatedGuest);
		};
	}

}