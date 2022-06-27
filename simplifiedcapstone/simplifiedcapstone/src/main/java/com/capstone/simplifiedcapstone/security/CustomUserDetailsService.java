package com.capstone.simplifiedcapstone.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.capstone.simplifiedcapstone.models.User;
import com.capstone.simplifiedcapstone.repositories.UserRepository;


public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByEmail(username);
		if(user==null) {
			throw new UsernameNotFoundException("User not found");
		}
		return new CustomUserDetails(user);
	}
}
