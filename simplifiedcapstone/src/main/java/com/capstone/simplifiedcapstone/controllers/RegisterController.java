package com.capstone.simplifiedcapstone.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.capstone.simplifiedcapstone.models.Event;
import com.capstone.simplifiedcapstone.models.User;
import com.capstone.simplifiedcapstone.repositories.UserRepository;
import com.capstone.simplifiedcapstone.services.UserServiceIMPL;

@Controller
@RequestMapping("/register")
public class RegisterController {
	
//	@Autowired
//	private UserRepository userRepo;
	
	@Autowired
	private UserServiceIMPL userService;
	
	@GetMapping("")
	public ModelAndView showRegisterForm() {
		ModelAndView mav = new ModelAndView("register-user-form");
		User user = new User();
		mav.addObject("user", user);
		return mav;
	}
	
	@PostMapping("/saveUser")
	public String registerUser(@ModelAttribute User user) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		
		userService.save(user);
		return "register-success";
	}
	
	@GetMapping("/roles")
	public ModelAndView showRoleForm(@RequestParam Long userId) {
		ModelAndView mav = new ModelAndView("register-role-form");
		User user = userService.findById(userId);
		mav.addObject("user", user);
		return mav;
	}
	
}
