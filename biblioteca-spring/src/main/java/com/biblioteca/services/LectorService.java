package com.biblioteca.services;

import java.util.List;

import com.biblioteca.entities.Lector;

public interface LectorService {

	Lector listarId(long id); //findById del repositorio
	List<Lector> listar();  // findAll del repo
	List<Lector> listarEnabled();
	Lector agregar(Lector u); // save del repo
	Lector modificar(Lector u); // save del repo
	void delete(long id); // delete(Lector); del repo
	
}
