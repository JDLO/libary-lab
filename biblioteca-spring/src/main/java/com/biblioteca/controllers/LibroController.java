package com.biblioteca.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.biblioteca.entities.Libro;
import com.biblioteca.entities.TipoLibro;
import com.biblioteca.services.AutorService;
import com.biblioteca.services.LibroService;

@Controller
public class LibroController {
	
	@Autowired
	private LibroService libroService;
	@Autowired
	private AutorService autorService;
	
	@GetMapping("/libro/list")
	public String viewHomePagelibro(Model model) {
		List<Libro> listlibros = libroService.listar();
		model.addAttribute("listBooks", listlibros);
		return "libro/list";
	}
	
	@PostMapping("/libro/save/{id}")
	public String savelibro(@PathVariable("id") Long idAutor,@ModelAttribute("book") Libro libro) {
		libro.setAutor(autorService.listarId(idAutor));
		libroService.agregar(libro);
		return "redirect:/libro/list";
	}
	
	@GetMapping("/libro/delete/{id}")
	public String deletelibro(@PathVariable("id") long id) {
		this.libroService.delete(id);
		return "redirect:/libro/list";
	}
	
	@GetMapping("/libro/update/{id}")
	public String showFormUpdate(@PathVariable("id") long id, Model model) {
		Libro libro=this.libroService.listarId(id);
		model.addAttribute("book", libro);
		model.addAttribute("listTiposLibros", TipoLibro.values());
		return "libro/update";
	}
	
	@GetMapping("/libro/add/{id}")
	public String showNewCrusoForm(@PathVariable("id")Long idAutor, Model model) {
		Libro libro = new Libro();
		model.addAttribute("book", libro);
		model.addAttribute("idAutor", idAutor);
		model.addAttribute("listTiposLibros", TipoLibro.values());
		return "libro/add";
	}	
}
