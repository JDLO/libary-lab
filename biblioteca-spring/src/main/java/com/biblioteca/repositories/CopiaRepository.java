package com.biblioteca.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biblioteca.entities.Copia;

public interface CopiaRepository extends JpaRepository<Copia, Long>{
	
	Copia findById(long id);

	Copia save(Copia u);

	void delete(Copia u);

	List<Copia> findAll();

}
