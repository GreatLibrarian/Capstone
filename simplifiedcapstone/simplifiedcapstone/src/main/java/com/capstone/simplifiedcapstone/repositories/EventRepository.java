package com.capstone.simplifiedcapstone.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capstone.simplifiedcapstone.models.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

//	This query selects a List of Event IDs whose listed Types match with a given User's listed Types at least once
	@Query(value = "SELECT DISTINCT event_event_id FROM event_types et INNER JOIN user_types ut WHERE et.types_type_id = ut.types_type_id AND ut.user_user_id = :id ORDER BY event_event_id ASC", nativeQuery=true)
	public List<Long> findMatchingIds(Long id);
}
