package com.capstone.simplifiedcapstone.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.simplifiedcapstone.models.Event;
import com.capstone.simplifiedcapstone.repositories.EventRepository;

@Service
public class EventServiceIMPL implements EventService {
	
	@Autowired
	private EventRepository eventRepo;

	public List<Event> findAll() {
		// TODO Auto-generated method stub
		return eventRepo.findAll();
	}
	
	public void save(Event event) {
		eventRepo.save(event);
	}
}
