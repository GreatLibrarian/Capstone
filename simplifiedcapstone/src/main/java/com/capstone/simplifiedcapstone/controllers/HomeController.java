package com.capstone.simplifiedcapstone.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.capstone.simplifiedcapstone.models.User;
import com.capstone.simplifiedcapstone.services.UserServiceImpl;

@Controller
public class HomeController {
	
	@Autowired
	private UserServiceImpl userService;
	
//	Home Page for the program
	@GetMapping({"", "/", "/home"})
	public ModelAndView root() {
		ModelAndView mav = new ModelAndView("index");
		User user = userService.getLoggedUser();
		mav.addObject("user", user);
		return mav;
	}
	
}
