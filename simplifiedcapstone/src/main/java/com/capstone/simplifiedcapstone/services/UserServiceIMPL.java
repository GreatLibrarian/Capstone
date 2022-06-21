package com.capstone.simplifiedcapstone.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.capstone.simplifiedcapstone.models.User;
import com.capstone.simplifiedcapstone.repositories.UserRepository;

@Service
public class UserServiceIMPL implements UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	public List<User> findAll() {
		return userRepo.findAll();
	}

	public User findById(Long userId) {
		// TODO Auto-generated method stub
		return userRepo.findById(userId).get();
	}

	public void save(User user) {
		// TODO Auto-generated method stub
		userRepo.save(user);
	}
	
	public User findByEmail(String username) {
		return userRepo.findByEmail(username);
	}
	
	public User getLoggedUser() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username;
		if(principal instanceof UserDetails) {
			username = ((UserDetails)principal).getUsername();
		} else {
			username = principal.toString();
		}
		
		return findByEmail(username);
	}
	
}
