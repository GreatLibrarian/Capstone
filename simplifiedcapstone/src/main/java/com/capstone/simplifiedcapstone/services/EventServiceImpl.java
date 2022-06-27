package com.capstone.simplifiedcapstone.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.simplifiedcapstone.models.Event;
import com.capstone.simplifiedcapstone.models.User;
import com.capstone.simplifiedcapstone.repositories.EventRepository;

@Service
public class EventServiceImpl implements EventService {
	
	@Autowired
	private EventRepository eventRepo;

//	This is used mainly to display events which match the user's given Type preferences
	public List<Event> findMatchingTypes(User user) {
		
//		This was my old method I was trying, which I replaced with a custom query instead because it's more efficient
//		List<Event> events = findAll();
//		List<Event> result = new ArrayList<Event>();
//		List<Type> usertypes = user.getTypes();
//		
//		for(Event event : events) {
//			for(Type usertype : usertypes) {
//				if(event.getTypes().contains(usertype)) {
//					result.add(event);
//					break;
//				}
//			}
//		}
		
//		Returns all Events by ID (Returns IDs of Events with a matching type to a User Id (returns the user's ID)
		List<Event> result = eventRepo.findAllById(eventRepo.findMatchingIds(user.getId()));
		
		return result;
	}
	
	public List<Event> findAll() {
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
