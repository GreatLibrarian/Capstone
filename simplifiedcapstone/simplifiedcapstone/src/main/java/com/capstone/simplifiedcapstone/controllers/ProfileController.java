package com.capstone.simplifiedcapstone.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.capstone.simplifiedcapstone.models.Role;
import com.capstone.simplifiedcapstone.models.Type;
import com.capstone.simplifiedcapstone.models.User;
import com.capstone.simplifiedcapstone.services.RoleServiceImpl;
import com.capstone.simplifiedcapstone.services.TypeServiceImpl;
import com.capstone.simplifiedcapstone.services.UserServiceImpl;

@Controller
@RequestMapping("/profile")
public class ProfileController {
	
	@Autowired
	private UserServiceImpl userService;
	
	@Autowired
	private RoleServiceImpl roleService;
	
	@Autowired
	private TypeServiceImpl typeService;
	
	
	@GetMapping("")
	public ModelAndView showProfile() {
		ModelAndView mav = new ModelAndView("profile-page");
//		getLoggedUser is a Service method that returns the currently logged in user entity
		User user = userService.getLoggedUser();
		mav.addObject("user", user);
		return mav;
	}
	
	@GetMapping("/showUpdateForm")
	public ModelAndView showUpdateForm() {
		ModelAndView mav = new ModelAndView("register-user-form");
		User user = userService.getLoggedUser();
		mav.addObject("user", user);
		return mav;
	}
	
	@PostMapping("/updateUser")
	public String updateUser(@ModelAttribute User user,
			RedirectAttributes redirectAttributes) {
		User olduser = userService.findById(user.getId());
		user.setPassword(olduser.getPassword());
		user.setRoles(olduser.getRoles());
		user.setTypes(olduser.getTypes());
		try {
			userService.save(user);
		} catch(Exception e) {
			redirectAttributes.addFlashAttribute("emailDuplicate", "This email is already in use.");
			e.printStackTrace();
			return "redirect:/profile/showUpdateForm";
		}
		
		return "update-success";
	}
	
	@GetMapping("/showRoleForm")
	public ModelAndView getRoleForm(@RequestParam Long userId) {
		ModelAndView mav = new ModelAndView("profile-role-form");
		
		User user = userService.findById(userId);
		mav.addObject("user", user);
		List<Role> roles = roleService.findAll();
		mav.addObject("roles", roles);
		List<Type> types = typeService.findAll();
		mav.addObject("types", types);
		
		return mav;
	}
	
	@PostMapping("/updateRoles")
	public String updateRoles(@ModelAttribute User user,
			@RequestParam(value = "rolers", required=false) List<Role> roles,
			@RequestParam(value = "typers", required=false) List<Type> types) {
		User olduser = userService.findById(user.getId());
		user.setEmail(olduser.getEmail());
		user.setFirstName(olduser.getFirstName());
		user.setLastName(olduser.getLastName());
		user.setPassword(olduser.getPassword());
		if(roles != null) {
			user.setRoles(roles);
		} else {
			user.setRoles(olduser.getRoles());
		}
		if(types != null ) {
			user.setTypes(types);
		} else {
			user.setTypes(olduser.getTypes());
		}
		
		
//		user.setRoles(roles);
		
		userService.save(user);
		
		return "update-success";
	}
}
