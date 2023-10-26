package com.biblioteca.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.biblioteca.entities.Autor;
import com.biblioteca.services.AutorService;
import com.biblioteca.util.DateFormmatter;

@Controller
public class AutorController {
		
	@Autowired
	private AutorService autorService;
	
	@GetMapping("/autor")
	public String viewHomePageAutor(Model model) {
		List<Autor> listAutors = autorService.listar();
		model.addAttribute("listAuthors", listAutors);
		return "autor/list";
	}
	
	@PostMapping("/autor/save")
	public String saveAutor(@ModelAttribute("author") Autor autor) {
		autorService.agregar(autor);
		return "redirect:/autor";
	}
	
	@GetMapping("/autor/delete/{id}")
	public String deleteAutor(@PathVariable("id") long id) {
		this.autorService.delete(id);
		return "redirect:/autor";
	}
	
	@GetMapping("/autor/update/{id}")
	public String showFormUpdate(@PathVariable("id") long id, Model model) {
		Autor autor=this.autorService.listarId(id);
		model.addAttribute("author", autor);
		return "autor/update";
	}
	
	@GetMapping("/autor/add")
	public String showNewCrusoForm(Model model) {
		Autor autor = new Autor();
		model.addAttribute("author", autor);
		return "autor/add";
	}	

}