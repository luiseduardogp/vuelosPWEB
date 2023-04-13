package com.practicaswrest.repo;

import com.practicaswrest.Modelo.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightReposity extends JpaRepository<Flight,Integer> {
}
