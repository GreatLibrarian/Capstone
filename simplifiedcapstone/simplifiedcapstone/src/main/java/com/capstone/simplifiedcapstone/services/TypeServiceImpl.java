package com.capstone.simplifiedcapstone.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.simplifiedcapstone.models.Type;
import com.capstone.simplifiedcapstone.repositories.TypeRepository;

@Service
public class TypeServiceImpl implements TypeService {
	
	@Autowired
	private TypeRepository typeRepo;
	
	public List<Type> findAll() {
		return typeRepo.findAll();
	}
	
	public Type findById(Long typeId) {
		return typeRepo.findById(typeId).get();
	}
}
