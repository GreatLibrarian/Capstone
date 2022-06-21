package com.capstone.simplifiedcapstone.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.capstone.simplifiedcapstone.models.Event;
import com.capstone.simplifiedcapstone.models.User;
import com.capstone.simplifiedcapstone.repositories.EventRepository;
import com.capstone.simplifiedcapstone.services.EventServiceIMPL;
import com.capstone.simplifiedcapstone.services.UserServiceIMPL;

@Controller
@RequestMapping("/events")
public class EventController {
	
//	@Autowired
//	private EventRepository eventRepo;
	
	@Autowired
	private EventServiceIMPL eventService;
	
	@Autowired
	private UserServiceIMPL userService;
	
	@GetMapping("")
	public ModelAndView showEvents() {
		ModelAndView mav = new ModelAndView("events-general-display");
		
		List<Event> events = eventService.findAll();
		mav.addObject("events", events);
		User user = userService.getLoggedUser();
		mav.addObject("user", user);
		
		return mav;
	}
	
	@GetMapping("showEventForm")
	public ModelAndView showEventForm() {
		ModelAndView mav = new ModelAndView("event-adder-form");
		Event event = new Event();
		mav.addObject("event", event);
		
		return mav;
	}
	
	@PostMapping("/addEvent")
	public String addEvent(@ModelAttribute Event event) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username;
		if(principal instanceof UserDetails) {
			username = ((UserDetails)principal).getUsername();
		} else {
			username = principal.toString();
		}
		
		User user = userService.findByEmail(username);
		event.setUser(user);
		
		eventService.save(event);
		return "event-add-success";
	}
}
