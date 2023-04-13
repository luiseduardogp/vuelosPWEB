package com.practicaswrest.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practicaswrest.Modelo.Rol;

public interface RolRepositorio extends JpaRepository<Rol, Long>{

	public Optional<Rol> findByNombre(String nombre);
	
}
