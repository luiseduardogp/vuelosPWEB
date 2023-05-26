package com.practicaswrest.repo;

import com.practicaswrest.Modelo.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Repository
public interface FlightReposity extends JpaRepository<Flight,Integer> {

}

