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
import com.biblioteca.services.LibroService;

@Controller
public class LibroController {
	
	@Autowired
	private LibroService libroService;
	
	@GetMapping("/libro/list")
	public String viewHomePagelibro(Model model) {
		List<Libro> listlibros = libroService.listar();
		model.addAttribute("listBooks", listlibros);
		return "libro/list";
	}
	
	@PostMapping("/libro/save")
	public String savelibro(@ModelAttribute("book") Libro libro) {
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
		return "libro/update";
	}
	
	@GetMapping("/libro/add")
	public String showNewCrusoForm(Model model) {
		Libro libro = new Libro();
		model.addAttribute("book", libro);
		return "libro/add";
	}	
}
