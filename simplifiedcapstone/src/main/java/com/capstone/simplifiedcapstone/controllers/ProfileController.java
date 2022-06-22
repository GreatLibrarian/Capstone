package com.capstone.simplifiedcapstone.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.capstone.simplifiedcapstone.models.Role;
import com.capstone.simplifiedcapstone.models.Type;
import com.capstone.simplifiedcapstone.models.User;
import com.capstone.simplifiedcapstone.services.RoleServiceIMPL;
import com.capstone.simplifiedcapstone.services.TypeServiceIMPL;
import com.capstone.simplifiedcapstone.services.UserServiceIMPL;

@Controller
@RequestMapping("/profile")
public class ProfileController {
	
	@Autowired
	private UserServiceIMPL userService;
	
	@Autowired
	private RoleServiceIMPL roleService;
	
	@Autowired
	private TypeServiceIMPL typeService;
	
	@GetMapping("")
	public ModelAndView showProfile() {
		ModelAndView mav = new ModelAndView("profile-page");
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username;
		if(principal instanceof UserDetails) {
			username = ((UserDetails)principal).getUsername();
		} else {
			username = principal.toString();
		}
		User user = userService.findByEmail(username);
		mav.addObject("user", user);
		return mav;
	}
	
	@GetMapping("/showUpdateForm")
	public ModelAndView showUpdateForm(@RequestParam Long userId) {
		ModelAndView mav = new ModelAndView("register-user-form");
		User user = userService.findById(userId);
		mav.addObject("user", user);
		return mav;
	}
	
	@PostMapping("/updateUser")
	public String updateUser(@ModelAttribute User user) {
		User olduser = userService.findById(user.getId());
		user.setPassword(olduser.getPassword());
		user.setRoles(olduser.getRoles());
		user.setTypes(olduser.getTypes());
		userService.save(user);
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
