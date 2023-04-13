package com.practicaswrest.repo;


import com.practicaswrest.Modelo.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingReposity extends JpaRepository<Booking,Integer> {
}
