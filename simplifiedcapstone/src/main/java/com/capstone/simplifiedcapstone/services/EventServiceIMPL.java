package com.capstone.simplifiedcapstone.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.simplifiedcapstone.models.Event;
import com.capstone.simplifiedcapstone.models.Type;
import com.capstone.simplifiedcapstone.models.User;
import com.capstone.simplifiedcapstone.repositories.EventRepository;

@Service
public class EventServiceIMPL implements EventService {
	
	@Autowired
	private EventRepository eventRepo;

	public List<Event> findMatchingTypes(User user) {
		
		List<Event> events = findAll();
		List<Event> result = new ArrayList<Event>();
		List<Type> usertypes = user.getTypes();
		
		for(Event event : events) {
			for(Type usertype : usertypes) {
				if(event.getTypes().contains(usertype)) {
					result.add(event);
					break;
				}
			}
		}
		
		return result;
	}
	
	public List<Event> findAll() {
		// TODO Auto-generated method stub
		return eventRepo.findAll();
	}
	
	public void save(Event event) {
		eventRepo.save(event);
	}
	
	public void delete(Event event) {
		eventRepo.delete(event);
	}
	
	public void deleteById(Long eventId) {
		eventRepo.deleteById(eventId);
	}
	
	public Event findById(Long id) {
		return eventRepo.findById(id).get();
	}
}
