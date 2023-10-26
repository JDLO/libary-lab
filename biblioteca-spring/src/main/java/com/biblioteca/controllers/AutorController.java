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

@Controller
public class AutorController {
	
	private String path = "/autor";
	
	@Autowired
	private AutorService autorService;
	
	@GetMapping("/autor")
	public String viewHomePageAutor(Model model) {
		return findPaginated(1, "nombre", "asc", model);
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
		return "actualizar_autor";
	}
	
	@GetMapping("/autor/add")
	public String showNewCrusoForm(Model model) {
		Autor autor = new Autor();
		model.addAttribute("author", autor);
		return "autor/create_autor";
	}
	
	@GetMapping("/autor/page/{pageNo}")
	public String findPaginated(
			@PathVariable(value="pageNo")int pageNro,
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model
			) {
		
		int pageSize = 4;
		Page<Autor> page = autorService.findPaginated(pageNro, pageSize, sortField, sortDir);
		List<Autor> listAutors = page.getContent();
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("sortField", sortField);
		model.addAttribute("currentPage", pageNro);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("listAuthors", listAutors);
		model.addAttribute("reverseSortDir", sortDir.equals("asc")?"desc":"asc");
		
		return "autor/autor_home";
	}

}
