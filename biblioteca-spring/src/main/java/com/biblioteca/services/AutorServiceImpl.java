package com.biblioteca.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.biblioteca.entities.Autor;
import com.biblioteca.repositories.AutorRepository;

@Service
public class AutorServiceImpl implements AutorService {

	@Autowired
	private AutorRepository repositorio;

	@Override
	public Autor listarId(long id) {
		return repositorio.findById(id);
	}

	@Override
	public List<Autor> listar() {
		return repositorio.findAll();
	}

	@Override
	public Autor agregar(Autor u) {
		return repositorio.save(u);
	}

	@Override
	public Autor modificar(Autor a) {
		Autor original = repositorio.findById(a.getId());
		if (a.getNombre() != null) {
			original.setNombre(a.getNombre());
		}
		if (a.getNacionalidad() != null) {
			original.setNacionalidad(a.getNacionalidad());
		}
		if (a.getFechaNacimiento() != null) {
			original.setFechaNacimiento(a.getFechaNacimiento());
		}
		return repositorio.save(original);
	}

	@Override
	public void delete(long id) {
		Autor u = repositorio.findById(id);
		if (u != null) {
			repositorio.delete(u);
		}

	}

	@Override
	public Page<Autor> findPaginated(int pageNum, int pageSize, String sortField, String sortDirection) {
		Sort sort=sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())?
			    Sort.by(sortField).ascending():
				Sort.by(sortField).descending();
				
	Pageable pageable=PageRequest.of(pageNum -1, pageSize, sort);
	
	return this.repositorio.findAll(pageable);
	}
}
