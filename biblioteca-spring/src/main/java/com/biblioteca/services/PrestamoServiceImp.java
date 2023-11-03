package com.biblioteca.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.entities.Copia;
import com.biblioteca.entities.EstadoCopia;
import com.biblioteca.entities.Lector;
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

	@Autowired
	private MultaService multaService;

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

	// TODO con esta implementacion, se cree que la tabla es temporal
	/**
	 * Realiza un préstamo de un libro por su ID. Si el lector ya tiene 3 préstamos
	 * activos, no se le permite más préstamos.
	 *
	 * @param libroId  El ID del libro que se va a prestar.
	 * @param idLector El ID del lector al que se le asignará el préstamo.
	 * @return El nuevo préstamo creado o null si no se puede realizar el préstamo.
	 */
	@Override
	public Prestamo prestarByIdLibro(long libroId, long idLector) {
		return asignarCopiaALector(lectorService.listarId(idLector), copiaService.listarDisponiblesByIdLibro(libroId));
	}

	// TODO con esta implementacion, se cree que la tabla es temporal
	/**
	 * Realiza un préstamo de un libro por su título. Si el lector ya tiene 3
	 * préstamos activos, no se le permite más préstamos.
	 *
	 * @param titulo   El título del libro que se va a prestar.
	 * @param idLector El ID del lector al que se le asignará el préstamo.
	 * @return El nuevo préstamo creado o null si no se puede realizar el préstamo.
	 */
	@Override
	public Prestamo prestarByTituloLibro(String titulo, long idLector) {
		return asignarCopiaALector(lectorService.listarId(idLector),
				copiaService.listarDisponiblesByTituloLibro(titulo));
	}

	/**
	 * Asigna una copia a un lector, actualizando el estado de la copia a PRESTADO y
	 * creando un nuevo préstamo con la fecha de inicio actual.
	 *
	 * @param lector El lector al que se asignará la copia.
	 * @param copia  La copia a prestar.
	 * @return El nuevo préstamo creado.
	 */
	private Prestamo asignarCopiaALector(Lector lector, List<Copia> copiasDisponibles) {
		// Si el lector tiene mas de 3 prestamos, no se le puede dar otro prestamo
		if (lector.getPrestamos().size() > 3) {
			return null;
		}

		if (copiasDisponibles.isEmpty()) {
			// Si no existen copias de ese libro, devolver nulo
			return null;
		}

		// Actualizamos el estado de la copia a PRESTADO
		Copia copiaAPrestar = copiasDisponibles.get(0);
		copiaAPrestar.setEstado(EstadoCopia.PRESTADO);
		copiaService.agregar(copiaAPrestar);

		// Creamos el prestamo y ponemos como fecha de inicio la actual
		Prestamo prestamo = new Prestamo(LocalDate.now(), null);
		prestamo.setCopia(copiaAPrestar);
		prestamo.setLector(lector);
		this.agregar(prestamo);

		// TODO creo que no hace falta
//			Agregar el préstamo al lector
//		    lector.getPrestamos().add(prestamo);
//		    
//		    lectorService.agregar(lector);
		return prestamo;
	}

	@Override
	public List<Prestamo> listarPrestamosLector(long idLector) {
		return repositorio.findByLectorId(idLector);
	}

	/**
	 * Devuelve una lista con todos los prestamos que ha tenido el lector
	 *
	 * @param idLector El ID del lector.
	 */
	@Override
	public List<Prestamo> listarPrestamosActualesLector(long idLector) {
		return repositorio.findActualesByLectorId(idLector);
	}
	
	@Override
	public boolean devolver(Long idPrestamo) {
		Optional<Prestamo> optPrestamo = repositorio.findById(idPrestamo);
		// Si el prestamo no existe, devolver false
		if (!optPrestamo.isPresent()) {
			return false;
		}
		
		// Establece siempre la misam fecha y hora 
		LocalDate fechaActual = LocalDate.now();

		// Si el prestamo existe
		Prestamo prestamo = optPrestamo.get();
		
		// Establece si el prestamo tiene o no una multa
		prestamo = seValidaSiHayMulta(prestamo, fechaActual);

		// Establezco la fecha de devolucion a la actual
		prestamo.setFin(fechaActual);

		// Actualizo el estado de la copia a BIBLIOTECA
		prestamo.getCopia().setEstado(EstadoCopia.BIBLIOTECA);

		// Guardo los cambios en la base de datos
		this.agregar(prestamo);

		return true;
	}

	@Override
	public List<Prestamo> listarPrestamosMorosos(LocalDate fechaActual) {
		return repositorio.findAllPrestamoMoroso(fechaActual);
	}
	
	public Prestamo seValidaSiHayMulta(Prestamo prestamo,LocalDate fechaActual) {
		List<Prestamo> listPrestamosMorosos = this.listarPrestamosMorosos(fechaActual);
		if (listPrestamosMorosos.contains(prestamo)) {
			// Se crea siempre una multa nueva siendo siempre la mas actual
			multaIsCreated(prestamo, fechaActual);			
		}else {
			if(prestamo.getLector().getMulta() != null) {
				// Se cambia a null la multa si el lector no es moroso con su ultima entrega
				prestamo.getLector().setMulta(null);
			}
		}
		return prestamo;
	}
	
	public boolean multaIsCreated(Prestamo prestamo, LocalDate fechaActual) {
		return this.multaService.actualizarMultasSistema(prestamo, fechaActual);
	}
}