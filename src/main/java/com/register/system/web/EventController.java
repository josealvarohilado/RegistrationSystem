package com.register.system.web;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.register.system.config.ApplicationProperties;
import com.register.system.model.Event;
import com.register.system.model.Events;
import com.register.system.model.Locations;
import com.register.system.repositories.EventRepo;
import com.register.system.repositories.LocationRepo;

@Controller
@EnableConfigurationProperties(ApplicationProperties.class)
public class EventController {
	@Autowired
	ApplicationProperties appProperties;
	
	@Autowired
	EventRepo eventRepo;
	
	@Autowired
	LocationRepo locRepo;
	
	@Autowired
	RestTemplate restTemplate;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
	
	public Events getEvents(String eventName) {
		return restTemplate.getForObject(appProperties.getEventSearchUrl(eventName), Events.class);
	}
	
	@RequestMapping(path="/searchEvent")
	public ModelAndView searchEvent(@RequestParam String eventName) {
		ModelAndView mv = new ModelAndView("home");
		// List<Event> searchResult = eventRepo.findByeventNameWildcard(eventName);
		mv.addObject("eventSearch", getEvents(eventName).getEvents());
		return mv;
	}
	
	@RequestMapping("/newEvent")
	public String newEvent(Model model) {
		model.addAttribute("event",new Event());
		model.addAttribute("location", new Locations());
		model.addAttribute("locations",locRepo.findAll());
		model.addAttribute("eventAction","Create Event");
		model.addAttribute("action","createEvent");
		return "event";
	}
	
	@RequestMapping(value="/createEvent", method=RequestMethod.POST)
	public String createEvent(@ModelAttribute("event") Event event, Model model) {
		model.addAttribute("eventConfirmation","Event Created!");
		eventRepo.save(event);
		return "redirect:newEvent";
	}
	
	@RequestMapping(value="/addLocation", method=RequestMethod.POST)
	public String addLocation(@ModelAttribute("location") Locations location) {
		locRepo.save(location);
		return "redirect:newEvent";
	}
	
	//	Retrieve Event to update
	@RequestMapping("/event")
	public ModelAndView event(@RequestParam int eid) {
		Event event = eventRepo.findById(eid).orElse(new Event());
		ModelAndView mv = new ModelAndView("event");
		mv.addObject("eventAction","Update Event");
		mv.addObject("action","updateEvent");
		mv.addObject("event",event);
		mv.addObject("location",new Locations());
		mv.addObject("locations",locRepo.findAll());
		return mv;
	}
	
	@PostMapping("/updateEvent")
	public ModelAndView updateEvent(Event event) {
		ModelAndView mv = new ModelAndView("event");
		String returnMsg = event.toString();
		if(eventRepo.findById(event.getEid()) != null) {
			eventRepo.save(event);
			returnMsg = "Event Updated!";
		}
		else returnMsg = "Event record not existing!";
		mv.addObject("returnMsg",returnMsg);
		mv.addObject("location",new Locations());
		mv.addObject("locations",locRepo.findAll());
		mv.addObject("eventAction","Update Event");
		return mv;
	}
	
	@RequestMapping("/deleteEvent")
	public ModelAndView deleteEvent(@RequestParam int eid) {
		ModelAndView mv = new ModelAndView("home");
		restTemplate.delete(appProperties.getEvent(eid));
		mv.addObject("eventSearch",getEvents("").getEvents());
		return mv;
	}
}
