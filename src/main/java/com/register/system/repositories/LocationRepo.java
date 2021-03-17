package com.register.system.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.register.system.model.Locations;

public interface LocationRepo extends JpaRepository<Locations, Integer>{

}
