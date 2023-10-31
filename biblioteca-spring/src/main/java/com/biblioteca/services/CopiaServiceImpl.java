package com.biblioteca.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.entities.Copia;
import com.biblioteca.entities.EstadoCopia;
import com.biblioteca.repositories.CopiaRepository;

@Service
public class CopiaServiceImpl implements CopiaService {

	@Autowired
	private CopiaRepository repositorio;

	@Override
	public Copia listarId(long id) {
		return repositorio.findById(id);
	}

	@Override
	public List<Copia> listar() {
		return repositorio.findAll();
	}

	@Override
	public Copia agregar(Copia u) {
		return repositorio.save(u);
	}

	@Override
	public Copia modificar(Copia co) {
		Copia original = repositorio.findById(co.getId());
		if (co.getEstado() != null) {
			original.setEstado(co.getEstado());
		}
		return repositorio.save(original);
	}

	@Override
	public void delete(long id) {
		Copia u = repositorio.findById(id);
		if (u != null) {
			repositorio.delete(u);
		}
	}

	@Override
	public List<Copia> listarDisponiblesByLibroId(long libroId) {
		return repositorio.listarByLibroId(libroId, EstadoCopia.BIBLIOTECA);
	}

	@Override
	public List<Copia> listarByTituloLibro(String titulo) {
		return repositorio.listarByTituloLibro(titulo);
	}
}