package com.biblioteca.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.biblioteca.entities.Lector;

public interface LectorRepository extends JpaRepository<Lector, Long>{
	
	Lector findById(long id);

	Lector save(Lector u);

	void delete(Lector u);

	List<Lector> findAll();

	@Query("SELECT l FROM Lector l, User u WHERE l.id=u.id AND u.accountLocked=false")
	List<Lector> findAllEnabled();

}