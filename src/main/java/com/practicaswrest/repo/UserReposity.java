package com.practicaswrest.repo;


import com.practicaswrest.Modelo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserReposity extends JpaRepository<User,Integer> {
}
