package com.register.system.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.register.system.model.Guest;

public interface GuestRepo extends JpaRepository<Guest, Integer>{
	
	@Query("from Guest where firstName like %:guestName% or lastName like %:guestName%")
	List<Guest> findByNameWildcard(@Param("guestName") String guestName);

}
