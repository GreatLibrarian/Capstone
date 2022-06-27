package com.capstone.simplifiedcapstone.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capstone.simplifiedcapstone.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
	
//	Selects one Role by its name (role name is called "role" in the DB and Application)
	@Query("SELECT r FROM Role r WHERE r.role = ?1")
	public Role findByName(String role);
}
