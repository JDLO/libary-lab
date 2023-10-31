package com.biblioteca.controllers.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.entities.Copia;
import com.biblioteca.entities.EstadoCopia;
import com.biblioteca.entities.Libro;
import com.biblioteca.entities.Prestamo;
import com.biblioteca.services.CopiaService;
import com.biblioteca.services.LibroService;

@RestController
@RequestMapping("/rest/copia")
public class CopiaRestController {

	@Autowired
	private CopiaService copiaService;
	@Autowired
	private LibroService libroService;

	@GetMapping("/list")
	public List<Copia> ListarLibros() {
		List<Copia> listCopias = copiaService.listar();
		return listCopias;
	}

	@GetMapping("/{id}")
	public Copia listarCopiaId(@PathVariable("id") Long id) {
		return copiaService.listarId(id);
	}

	@PostMapping("/save/{id}")
	public Copia saveCopia(@PathVariable("id") Long idLibro, @RequestBody Copia copia) {
		copia.setLibro(libroService.listarId(idLibro));
		return copiaService.agregar(copia);
	}

	@PostMapping("/update/{id}")
	public Copia actualizarCopia(@PathVariable("id") long id,@RequestBody Copia model) {
		Copia copia = this.copiaService.listarId(id);
		if(model.getEstado() != null) {
			copia.setEstado(model.getEstado());
		}
		if (model.getLibro() != null) {
			copia.setLibro(model.getLibro());
		}
		if(model.getPrestamos() != null) {
			List<Prestamo> prestamos = copia.getPrestamos();
			for (Prestamo modelPrestamo: model.getPrestamos()) {
				if(!copia.getPrestamos().contains(modelPrestamo)) {
					prestamos.add(modelPrestamo);
				}
			}
			if(prestamos.size() > 0) {
				copia.setPrestamos(prestamos);				
			}
		}
		return copiaService.agregar(copia);
	}

	@PostMapping("/add/{id}")
	public List<Copia> saveCopias(
			@PathVariable("id") Long idLibro, 
			@RequestParam("cantidad") Long cantidad,
			@RequestParam("estadoCopia") String estado) {
		Libro libro = libroService.listarId(idLibro);
		EstadoCopia estadoLibro = EstadoCopia.valueOf(estado);
		List<Copia> listCopia = new ArrayList<Copia>();
		for (int i = 0; i < cantidad; i++) {
			Copia copia = new Copia();
			copia.setLibro(libro);
			copia.setEstado(estadoLibro);
			listCopia.add(copiaService.agregar(copia));
		}
		return listCopia;
	}

	@PostMapping("/delete/{id}")
	public void deletelibro(@PathVariable("id") long id) {
		// TODO Se debe agregar validacion para eliminar solo las copias sin asignar
		this.copiaService.delete(id);
	}
}
