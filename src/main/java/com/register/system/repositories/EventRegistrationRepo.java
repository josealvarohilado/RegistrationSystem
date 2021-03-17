package com.register.system.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.register.system.model.EventRegistration;
import com.register.system.model.RegistrationId;

public interface EventRegistrationRepo extends JpaRepository<EventRegistration, RegistrationId>{
	List<EventRegistration> findByPkGuestAid(int aid);
}
