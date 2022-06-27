package com.capstone.simplifiedcapstone;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.capstone.simplifiedcapstone.models.Event;
import com.capstone.simplifiedcapstone.models.Role;
import com.capstone.simplifiedcapstone.models.Type;
import com.capstone.simplifiedcapstone.models.User;
import com.capstone.simplifiedcapstone.services.EventServiceImpl;
import com.capstone.simplifiedcapstone.services.RoleServiceImpl;
import com.capstone.simplifiedcapstone.services.TypeServiceImpl;
import com.capstone.simplifiedcapstone.services.UserServiceImpl;

@SpringBootTest
public class ServiceTests {
	
	@Autowired
	private UserServiceImpl userService;
	
	@Autowired
	private TypeServiceImpl typeService;
	
	@Autowired
	private EventServiceImpl eventService;
	
	@Autowired
	private RoleServiceImpl roleService;
	
	@Test
	public void testFindById() {
//		Tests if findById method for all services works properly
		
		User user = userService.findById((long) 1);
		assertEquals("admin@admin.com", user.getEmail());
		
		Type type = typeService.findById((long) 1);
		assertEquals("Autism", type.getTypeName());
		
		Event event = eventService.findById((long) 1);
		assertEquals("Event", event.getName());
		
		Role role = roleService.findById((long) 1);
		assertEquals("Organizer", role.getRole());
	}
	
	@Test
	public void testFindMatchingTypes() {
		
		User user = userService.findById((long) 4);
		List<Event> actual = eventService.findMatchingTypes(user);
		
		List<Event> expected = new ArrayList<Event>();
		expected.add(eventService.findById((long) 2));
		expected.add(eventService.findById((long) 3));
		expected.add(eventService.findById((long) 4));
		
		assertEquals(expected.get(0).getName(), actual.get(0).getName());
		assertEquals(expected.get(1).getName(), actual.get(1).getName());
		assertEquals(expected.get(2).getName(), actual.get(2).getName());
	}
}
