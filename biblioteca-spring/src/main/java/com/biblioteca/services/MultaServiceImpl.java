package com.biblioteca.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.entities.Multa;
import com.biblioteca.repositories.MultaRepository;

@Service
public class MultaServiceImpl implements MultaService {

	@Autowired
	private MultaRepository repositorio;

	@Override
	public Multa listarId(long id) {
		return repositorio.findById(id);
	}

	@Override
	public List<Multa> listar() {
		return repositorio.findAll();
	}

	@Override
	public Multa agregar(Multa u) {
		return repositorio.save(u);
	}

	@Override
	public Multa modificar(Multa m) {
		Multa original = repositorio.findById(m.getId());
		if (m.getfInicio() != null) {
			original.setfInicio(m.getfInicio());
		}
		if (m.getfFin() != null) {
			original.setfFin(m.getfFin());
		}
		return repositorio.save(original);
	}

	@Override
	public void delete(long id) {
		Multa u = repositorio.findById(id);
		if (u != null) {
			repositorio.delete(u);
		}

	}

}
