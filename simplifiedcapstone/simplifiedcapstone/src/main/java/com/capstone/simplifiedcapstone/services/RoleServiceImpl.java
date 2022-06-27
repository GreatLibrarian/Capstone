package com.capstone.simplifiedcapstone.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.simplifiedcapstone.models.Role;
import com.capstone.simplifiedcapstone.repositories.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleRepository roleRepo;
	
	public List<Role> findAll() {
		return roleRepo.findAll();
	}

	public Role findById(Long roleId) {
		return roleRepo.findById(roleId).get();
	}

	public void save(Role role) {
		roleRepo.save(role);
	}
	
	public Role findByName(String role) {
		return roleRepo.findByName(role);
	}
}
