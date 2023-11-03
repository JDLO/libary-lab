package com.biblioteca.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.biblioteca.entities.Multa;

public interface MultaRepository extends JpaRepository<Multa, Long> {

	Multa findById(long id);

	Multa save(Multa u);

	void delete(Multa u);

	List<Multa> findAll();
}
