package com.biblioteca.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.entities.Libro;
import com.biblioteca.repositories.LibroRepository;

@Service
public class LibroServiceImpl implements LibroService {

	@Autowired
	private LibroRepository repositorio;

	@Override
	public Libro listarId(long id) {
		return repositorio.findById(id);
	}

	@Override
	public List<Libro> listar() {
		return repositorio.findAll();
	}

	@Override
	public Libro agregar(Libro u) {
		return repositorio.save(u);
	}

	@Override
	public Libro modificar(Libro li) {
		Libro original = repositorio.findById(li.getId());
		if (li.getTitulo() != null) {
			original.setTitulo(li.getTitulo());
		}
		if (li.getTipoLibro() != null) {
			original.setTipoLibro(li.getTipoLibro());
		}
		// TODO no se si hace falta comprobacion de algo
		original.setAnyo(li.getAnyo());
		return repositorio.save(original);
	}

	@Override
	public void delete(long id) {
		Libro u = repositorio.findById(id);
		if (u != null) {
			repositorio.delete(u);
		}

	}

}
