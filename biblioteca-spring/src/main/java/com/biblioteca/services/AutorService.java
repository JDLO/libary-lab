package com.biblioteca.services;

import java.util.List;

import com.biblioteca.entities.Autor;

public interface AutorService {

	Autor listarId(long id); //findById del repositorio
	List<Autor> listar();  // findAll del repo
	Autor agregar(Autor u); // save del repo
	Autor modificar(Autor u); // save del repo
	void delete(long id); // delete(Autor); del repo
	
}
