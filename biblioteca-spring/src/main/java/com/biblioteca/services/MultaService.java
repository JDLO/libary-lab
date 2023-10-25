package com.biblioteca.services;

import java.util.List;

import com.biblioteca.entities.Multa;

public interface MultaService {

	Multa listarId(long id); //findById del repositorio
	List<Multa> listar();  // findAll del repo
	Multa agregar(Multa u); // save del repo
	Multa modificar(Multa u); // save del repo
	void delete(long id); // delete(Multa); del repo
	
}
