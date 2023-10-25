package com.biblioteca.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biblioteca.entities.Libro;

public interface LibroRepository extends JpaRepository<Libro, Long>{
	
	Libro findById(long id);

	Libro save(Libro u);

	void delete(Libro u);

	List<Libro> findAll();

}
