package com.biblioteca.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.entities.Prestamo;
import com.biblioteca.repositories.PrestamoRepository;

@Service
public class PrestamoServiceImp implements PrestamoService {

	@Autowired
	private PrestamoRepository repositorio;

	@Override
	public Prestamo listarId(long id) {
		return repositorio.findById(id);
	}

	@Override
	public List<Prestamo> listar() {
		return repositorio.findAll();
	}

	@Override
	public Prestamo agregar(Prestamo u) {
		return repositorio.save(u);
	}

	@Override
	public Prestamo modificar(Prestamo pr) {
		Prestamo original = repositorio.findById(pr.getId());
		if (pr.getInicio() != null) {
			original.setInicio(pr.getInicio());
		}
		if (pr.getFin() != null) {
			original.setFin(pr.getFin());
		}
		return repositorio.save(original);
	}

	@Override
	public void delete(long id) {
		Prestamo u = repositorio.findById(id);
		if (u != null) {
			repositorio.delete(u);
		}

	}
}
