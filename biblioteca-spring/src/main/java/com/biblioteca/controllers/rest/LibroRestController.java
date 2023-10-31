package com.biblioteca.controllers.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.entities.Libro;
import com.biblioteca.services.AutorService;
import com.biblioteca.services.LibroService;

@RestController
@RequestMapping("/rest/libro")
public class LibroRestController {
	
	@Autowired
	private LibroService libroService;
	@Autowired
	private AutorService autorService;
	
	@GetMapping("/list")
	public List<Libro> listarLibros() {
		return libroService.listar();
	}

	@GetMapping("/{id}")
	public Libro listarId(@PathVariable("id") Long id) {
		Libro libro = libroService.listarId(id);
		return libro;
	}
	
	@PostMapping("/save/{id}")
	public Libro savelibro(@PathVariable("id") Long idAutor,@RequestBody Libro libro) {
		libro.setAutor(autorService.listarId(idAutor));
		libroService.agregar(libro);
		return libroService.agregar(libro);
	}
	
	@PostMapping("/delete/{id}")
	public void deletelibro(@PathVariable("id") long id) {
		this.libroService.delete(id);
	}
	
	@PutMapping("/update/{id}")
	public Libro updateLibro(@PathVariable("id") long id,@RequestBody Libro model) {
		Libro libro=this.libroService.listarId(id);
		if (model.getTitulo() != null) {
			libro.setTitulo(model.getTitulo());
		}
		if (model.getEditorial() != null) {
			libro.setEditorial(model.getEditorial());
		}
		if (model.getAnyo() > 0) {
			libro.setAnyo(model.getAnyo());
		}
		if (model.getTitulo() != null) {
			libro.setTitulo(model.getTitulo());
		}
		return libroService.agregar(libro);
	}
}
