package com.biblioteca.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.entities.Lector;
import com.biblioteca.repositories.LectorRepository;

@Service
public class LectorServiceImpl implements LectorService {

	@Autowired
	private LectorRepository repositorio;

	@Override
	public Lector listarId(long id) {
		return repositorio.findById(id);
	}

	@Override
	public List<Lector> listar() {
		return repositorio.findAll();
	}

	@Override
	public Lector agregar(Lector u) {
		return repositorio.save(u);
	}

	@Override
	public Lector modificar(Lector le) {
		Lector original = repositorio.findById(le.getnSocio());
		if (le.getNombre() != null) {
			original.setNombre(le.getNombre());
		}
		if (le.getTelefono() != null) {
			original.setTelefono(le.getTelefono());
		}
		if (le.getDireccion() != null) {
			original.setDireccion(le.getDireccion());
		}
		return repositorio.save(original);
	}

	@Override
	public void delete(long id) {
		Lector u = repositorio.findById(id);
		if (u != null) {
			repositorio.delete(u);
		}

	}
}
