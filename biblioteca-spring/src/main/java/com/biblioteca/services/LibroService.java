package com.biblioteca.services;

import java.util.List;

import com.biblioteca.entities.Libro;

public interface LibroService {

	Libro listarId(long id); //findById del repositorio
	List<Libro> listar();  // findAll del repo
	Libro agregar(Libro u); // save del repo
	Libro modificar(Libro u); // save del repo
	void delete(long id); // delete(Libro); del repo
	
}
