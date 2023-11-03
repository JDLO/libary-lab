package com.biblioteca.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.biblioteca.entities.Copia;
import com.biblioteca.entities.EstadoCopia;
import com.biblioteca.entities.Libro;
import com.biblioteca.services.CopiaService;
import com.biblioteca.services.LibroService;

@Controller
public class CopiaController {
	
	@Autowired
	private CopiaService copiaService;
	@Autowired
	private LibroService libroService;
	
	@GetMapping("/copia/list")
	public String viewHomePagelibro(Model model) {
		List<Copia> listCopias = copiaService.listar();
		model.addAttribute("listCopies", listCopias);
		model.addAttribute("listEstadoCopia", EstadoCopia.values());
		return "copia/list";
	}
	
	@PostMapping("/copia/update")
	public String savecopia(@ModelAttribute("copie") Copia copia) {
		copiaService.agregar(copia);
		return "redirect:/copia/list";
	}
	
	@PostMapping("/copia/add/{id}")
	public String saveCopias(
			@PathVariable("id") Long idLibro, 
			@RequestParam("cantidad") Long cantidad,
			@RequestParam("estadoCopia") String estado) {
		Libro libro = libroService.listarId(idLibro);
		EstadoCopia estadoLibro = EstadoCopia.valueOf(estado);
		for(int i = 0;i< cantidad; i++) {
			Copia copia = new Copia();
			copia.setLibro(libro);
			copia.setEstado(estadoLibro);
			copiaService.agregar(copia);	
		}
		return "redirect:/copia/list";
	}
	
	 // TODO: falta por resover dudas con el ESTADO COPIAS
	@GetMapping("/copia/update/{id}")
	public String showFormUpdate(@PathVariable("id") long id, Model model) {
		Copia copia=this.copiaService.listarId(id);
		model.addAttribute("copie", copia);
		EstadoCopia[] estadoCopia = {EstadoCopia.BIBLIOTECA, EstadoCopia.REPARACION};
		model.addAttribute("estadoCopia", estadoCopia);
		return "copia/update";
	}
	
	@GetMapping("/copia/add/{id}")
	public String showNewCrusoForm(@PathVariable("id")Long idAutor, Model model) {
		Libro libro = this.libroService.listarId(idAutor);
		model.addAttribute("book", libro);
		model.addAttribute("estadoCopia", EstadoCopia.BIBLIOTECA);
		return "copia/add";
	}
}
