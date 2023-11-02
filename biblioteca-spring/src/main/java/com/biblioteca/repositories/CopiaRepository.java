package com.biblioteca.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.biblioteca.entities.Copia;
import com.biblioteca.entities.EstadoCopia;
import com.biblioteca.entities.Libro;

public interface CopiaRepository extends JpaRepository<Copia, Long> {

	Copia findById(long id);

	Copia save(Copia u);

	void delete(Copia u);

	List<Copia> findAll();

	@Query(value = "SELECT c FROM Copia c WHERE c.libro.id = ?1 AND c.estado=?2")
	List<Copia> listarByLibroId(long id, EstadoCopia estado);
//
	@Query("SELECT c FROM Copia c, Libro l WHERE c.libro=l AND l.titulo=?1")
	List<Copia> listarByTituloLibro(String titulo);
}
