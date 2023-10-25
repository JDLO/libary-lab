package com.biblioteca.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biblioteca.entities.Prestamo;

public interface PrestamoRepository extends JpaRepository<Prestamo, Long>{
	
	Prestamo findById(long id);

	Prestamo save(Prestamo u);

	void delete(Prestamo u);

	List<Prestamo> findAll();

}
