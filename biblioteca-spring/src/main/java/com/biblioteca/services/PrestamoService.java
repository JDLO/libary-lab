package com.biblioteca.services;

import java.time.LocalDate;
import java.util.List;

import com.biblioteca.entities.Prestamo;

public interface PrestamoService {

	Prestamo listarId(long id); // findById del repositorio

	List<Prestamo> listar(); // findAll del repo

	Prestamo agregar(Prestamo u); // save del repo

	Prestamo modificar(Prestamo u); // save del repo

	void delete(long id); // delete(Prestamo); del repo 

	Prestamo prestarByIdLibro(long isbn, long idLector);

	Prestamo prestarByTituloLibro(String isbnTitulo, long idLector);
	
	List<Prestamo> listarPrestamosLector(long idLector);

	List<Prestamo> listarPrestamosActualesLector(long idLector);

	boolean devolver(Long idPrestamo);
	
	List<Prestamo> listarPrestamosMorosos(LocalDate fechaActual);
}