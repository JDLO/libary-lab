package com.biblioteca.services;

import java.util.List;

import com.biblioteca.entities.Prestamo;

public interface PrestamoService {

	Prestamo listarId(long id); //findById del repositorio
	List<Prestamo> listar();  // findAll del repo
	Prestamo agregar(Prestamo u); // save del repo
	Prestamo modificar(Prestamo u); // save del repo
	void delete(long id); // delete(Prestamo); del repo
	
}
