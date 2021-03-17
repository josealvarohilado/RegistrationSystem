package com.register.system.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.register.system.model.Event;

public interface EventRepo extends JpaRepository<Event, Integer>{
	List<Event> findByeventName(String eventName);
	
	@Query("from Event where eventName like %:eventName%")
	List<Event> findByeventNameWildcard(@Param("eventName") String eventName);
}
