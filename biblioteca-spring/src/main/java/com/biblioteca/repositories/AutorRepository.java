package com.biblioteca.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biblioteca.entities.Autor;

public interface AutorRepository extends JpaRepository<Autor, Long>{
	
	Autor findById(long id);

	Autor save(Autor u);

	void delete(Autor u);

	List<Autor> findAll();

}
