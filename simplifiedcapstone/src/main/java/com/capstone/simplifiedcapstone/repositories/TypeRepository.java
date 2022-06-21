package com.capstone.simplifiedcapstone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capstone.simplifiedcapstone.models.Type;

@Repository
public interface TypeRepository extends JpaRepository<Type, Long> {
	
}
