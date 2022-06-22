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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.capstone.simplifiedcapstone.models.Event;
import com.capstone.simplifiedcapstone.models.Type;
import com.capstone.simplifiedcapstone.models.User;
import com.capstone.simplifiedcapstone.repositories.EventRepository;
import com.capstone.simplifiedcapstone.services.EventServiceIMPL;
import com.capstone.simplifiedcapstone.services.TypeServiceIMPL;
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
	
	@Autowired
	private TypeServiceIMPL typeService;

	@GetMapping("")
	public ModelAndView showEvents() {
		ModelAndView mav = new ModelAndView("events-general-display");

		List<Event> events = eventService.findAll();
		mav.addObject("events", events);
		User user = userService.getLoggedUser();
		mav.addObject("user", user);
		if(user != null) {
			List<Event> preferredEvents = eventService.findMatchingTypes(user);
			mav.addObject("preferred", preferredEvents);
		}
		

		return mav;
	}

	@GetMapping("showEventForm")
	public ModelAndView showEventForm() {
		ModelAndView mav = new ModelAndView("event-adder-form");
		
		Event event = new Event();
		mav.addObject("event", event);
		List<Type> types = typeService.findAll();
		mav.addObject("types", types);

		return mav;
	}

	@PostMapping("/addEvent")
	public String addEvent(@ModelAttribute Event event,
			@RequestParam(value="typers",required=false) List<Type> types,
			RedirectAttributes redirectAttributes) {
//		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		String username;
//		if (principal instanceof UserDetails) {
//			username = ((UserDetails) principal).getUsername();
//		} else {
//			username = principal.toString();
//		}
//
//		User user = userService.findByEmail(username);
		User user = userService.getLoggedUser();
		event.setUser(user);
		if(types != null) {
			event.setTypes(types);
		}

		eventService.save(event);
		
		redirectAttributes.addFlashAttribute("successAdd", "Event Added Successfully");
		return "redirect:/events";
	}

	@GetMapping("/deleteEvent")
	public String deleteEvent(@RequestParam Long eventId,
			RedirectAttributes redirectAttributes) {
		User user = userService.getLoggedUser();
		if (user != eventService.findById(eventId).getUser()) {
			System.out.println("Cannot delete an event you don't own.");
			return "redirect:/events";
		}
		eventService.deleteById(eventId);
		redirectAttributes.addFlashAttribute("successDelete", "Successfully Removed Event");
		return "redirect:/events";
	}

	@GetMapping("/followEvent")
	public String followEvent(@RequestParam Long eventId,
			RedirectAttributes redirectAttributes) {
		User user = userService.getLoggedUser();
		Event event = eventService.findById(eventId);

		if (user == event.getUser()) {
			System.out.println("Cannot follow/unfollow your own event");
		} else {
			user.getFollowedEvents().add(event);
			userService.save(user);
		}
		
		redirectAttributes.addFlashAttribute("successFollow", "Successfully Followed");
		return "redirect:/events";
	}
	
	@GetMapping("/unfollowEvent")
	public String unfollowEvent(@RequestParam Long eventId,
			RedirectAttributes redirectAttributes) {
		User user = userService.getLoggedUser();
		Event event = eventService.findById(eventId);
		
		if(!user.getFollowedEvents().contains(event)) {
			System.out.println("Not following this event.");
			return "redirect:/events";
		}
		
		if(user == event.getUser()) {
			System.out.println("Cannot follow/unfollow your own event.");
		} else {
			user.getFollowedEvents().remove(event);
			userService.save(user);
		}
		
		redirectAttributes.addFlashAttribute("successUnfollow", "Successfully Unfollowed");
		return "redirect:/events";
	}
	
	@GetMapping("/profile")
	public ModelAndView showEventProfile(@RequestParam Long eventId) {
		ModelAndView mav = new ModelAndView("events-profile-page");
		Event event = eventService.findById(eventId);
		mav.addObject("event", event);
		return mav;
	}
}
