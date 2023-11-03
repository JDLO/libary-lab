package com.biblioteca.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.biblioteca.entities.Prestamo;

public interface PrestamoRepository extends JpaRepository<Prestamo, Long>{
	
	Prestamo findById(long id);

	Prestamo save(Prestamo u);

	void delete(Prestamo u);

	List<Prestamo> findAll();

	@Query("SELECT p FROM Prestamo p WHERE p.lector.id=?1")
	List<Prestamo> findByLectorId(long idLector);
	
	@Query(value = "SELECT * FROM prestamo WHERE DATE_ADD(inicio, INTERVAL 30 DAY) < :fechaHoy AND fin is NULL", nativeQuery  = true)
	List<Prestamo> findAllPrestamoMoroso(LocalDate fechaHoy);
	
	@Query("SELECT p FROM Prestamo p WHERE p.lector.id=?1 AND p.fin is null")
	List<Prestamo> findActualesByLectorId(long idLector);
}
