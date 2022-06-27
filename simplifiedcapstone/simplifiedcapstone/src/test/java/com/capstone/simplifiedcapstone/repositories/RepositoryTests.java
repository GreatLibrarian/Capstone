package com.capstone.simplifiedcapstone.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.capstone.simplifiedcapstone.models.Role;
import com.capstone.simplifiedcapstone.models.User;

@SpringBootTest
// This class is used to test the custom queries of my program
public class RepositoryTests {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private RoleRepository roleRepo;
	
	@Autowired
	private EventRepository eventRepo;
	
	@Test
	public void testFindByEmail() {
//		This method finds a user in the database by their email
		User actual = userRepo.findByEmail("admin@admin.com");
		
		assertEquals("Joe", actual.getFirstName());
		assertEquals("admin", actual.getLastName());
		assertEquals("admin@admin.com", actual.getEmail());
	}
	
	@Test 
	public void testFindByName() {
//		THis method finds a role in the database by its name
		Role actual = roleRepo.findByName("Organizer");
		assertEquals(1, actual.getId());
		
		actual = roleRepo.findByName("Attendee");
		assertEquals(2, actual.getId());
	}
	
	@Test
	public void testFindMatchingIds() {
//		User id 4, which is follower@follower.com, shares preferences with events 2, 3, and 4. This method returns
//		a list of longs that match the event IDs of events that share the user's preference.
		List<Long> actual = eventRepo.findMatchingIds((long) 4);
		List<Long> expected = new ArrayList<Long>();
		expected.add((long) 2);
		expected.add((long) 3);
		expected.add((long) 4);
		
		assertEquals(expected, actual);
	}
}
