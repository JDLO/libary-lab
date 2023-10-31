package com.biblioteca.controllers.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.entities.Autor;
import com.biblioteca.services.AutorService;

@RestController
@RequestMapping("rest/autor")
public class AutorRestController {
		
	@Autowired
	private AutorService autorService;
	
	@GetMapping("/list")
	public List<Autor> viewHomePageAutor() {
		List<Autor> listAutors = autorService.listar();
		return listAutors;
	}
	
	@GetMapping("/{id}")
	public Autor listarAutorId(@PathVariable("id")Long id) {
		Autor autor = autorService.listarId(id);
		return autor;
	}

	@PostMapping("/save")
	public Autor saveAutor(@RequestBody Autor autor) {
		return autorService.agregar(autor);
	}

	@PostMapping("/delete/{id}")
	public void deleteAutor(@PathVariable("id") long id) {
		this.autorService.delete(id);
	}

	@PutMapping("/update/{id}")
	public Autor updateAutor(@PathVariable("id") long id, @RequestBody Autor model) {
		Autor autor = this.autorService.listarId(id);
		if(model.getNombre() != null) {
			autor.setNombre(model.getNombre());
		}
		if(model.getFechaNacimiento() != null) {
			autor.setFechaNacimiento(model.getFechaNacimiento());
		}
		if(model.getNacionalidad() != null) {
			autor.setNacionalidad(model.getNacionalidad());
		}
		return autorService.agregar(autor);
	}
}
