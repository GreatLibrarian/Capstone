package com.capstone.simplifiedcapstone.controllers;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.capstone.simplifiedcapstone.models.User;
import com.capstone.simplifiedcapstone.services.UserServiceImpl;

@Controller
//All Mappings in this controller are preceded by "/register" before their URL path
@RequestMapping("/register")
public class RegisterController {
	
	@Autowired
	private UserServiceImpl userService;
	
//	Displays the register form
	@GetMapping("")
	public ModelAndView showRegisterForm() {
		ModelAndView mav = new ModelAndView("register-user-form");
		User user = new User();
		mav.addObject("user", user);
		return mav;
	}
	
//	Saves the user with an encrypted password in the DB
	@PostMapping("/saveUser")
	public String registerUser(@ModelAttribute User user,
			RedirectAttributes redirectAttributes) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		try {
			userService.save(user);
		} catch(Exception e) {
			redirectAttributes.addFlashAttribute("emailDuplicate", "This email is already in use.");
			e.printStackTrace();
			return "redirect:/register";
		}
		
//		Sends the user to a registration success page
		return "register-success";
	}
	
//	I originally was going to add roles as part of the registration process, but I instead switched it to profile customization instead
//	@GetMapping("/roles")
//	public ModelAndView showRoleForm(@RequestParam Long userId) {
//		ModelAndView mav = new ModelAndView("register-role-form");
//		User user = userService.findById(userId);
//		mav.addObject("user", user);
//		return mav;
//	}
	
}
