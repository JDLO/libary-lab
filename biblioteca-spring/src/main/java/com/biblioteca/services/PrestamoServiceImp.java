package com.biblioteca.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.entities.Copia;
import com.biblioteca.entities.EstadoCopia;
import com.biblioteca.entities.Lector;
import com.biblioteca.entities.Libro;
import com.biblioteca.entities.Prestamo;
import com.biblioteca.repositories.PrestamoRepository;

@Service
public class PrestamoServiceImp implements PrestamoService {

	@Autowired
	private PrestamoRepository repositorio;

	@Autowired
	private CopiaService copiaService;

	@Autowired
	private LectorService lectorService;

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

	@Override
	public Prestamo prestarByLibroId(long libroId, long idLector) {
		Lector lector = lectorService.listarId(idLector);

		// TODO con esta implementacion, se cree que la tabla es temporal
		// Si el lector tiene mas de 3 prestamos, no se le puede dar otro prestamo
		if (lector.getPrestamos().size() > 3) {
			return null;
		}

		// Obtenemos la lista con las copias disponibles para el libro con ese isbn
		List<Copia> copiasDisponibles = copiaService.listarDisponiblesByLibroId(libroId);
		if (copiasDisponibles.isEmpty()) {
			// Si no existen copias de ese libro, devolver nulo
			return null;
		} else {
			// Si existen copias, asignar la copia al lector y actualizar copia

			// Actualizamos el estado de la copia a PRESTADO
			Copia copiaAPrestar = copiasDisponibles.get(0);
			copiaAPrestar.setEstado(EstadoCopia.PRESTADO);
			copiaService.agregar(copiaAPrestar);

			// Creamos el prestamo y ponemos como fecha de inicio la actual
			Prestamo prestamo = new Prestamo(LocalDate.now(), null);
			prestamo.setCopia(copiaAPrestar);
			prestamo.setLector(lector);
			this.agregar(prestamo);

//			// Agregar el préstamo al lector
//		    lector.getPrestamos().add(prestamo);
//		    
//		    lectorService.agregar(lector);
			return prestamo;
		}
	}

	// TODO borrar
//	@Override
//	public List<Libro> listarLibrosDeLector(long idLector) {
//		Lector lector = lectorService.listarId(idLector);
//		if(lector == null) {
//			return null;
//		}
//		List<Libro> librosDelLector = new ArrayList<Libro>();
//		for (Prestamo prestamo : lector.getPrestamos()) {
//			librosDelLector.add(prestamo.getCopia().getLibro());
//		}
//		return librosDelLector;
//	}
	
	@Override
	public List<Prestamo> listarPrestamosDeLector(long idLector) {
		return repositorio.findByLectorId(idLector);
	}
}