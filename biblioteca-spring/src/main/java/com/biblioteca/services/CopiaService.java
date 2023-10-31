package com.biblioteca.services;

import java.util.List;

import com.biblioteca.entities.Copia;
import com.biblioteca.entities.EstadoCopia;

public interface CopiaService {

	Copia listarId(long id); //findById del repositorio
	List<Copia> listar();  // findAll del repoº
	Copia agregar(Copia u); // save del repo
	Copia modificar(Copia u); // save del repo
	void delete(long id); // delete(Copia); del repo
	List<Copia> listarDisponiblesByLibroId(long isbn);
	List<Copia> listarByTituloLibro(String isbnTitulo);
	
}
