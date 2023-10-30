package com.biblioteca.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.biblioteca.entities.Lector;
import com.biblioteca.services.LectorService;

@Controller
public class LectorController {

	@Autowired
	private LectorService lectorService;
	
	@GetMapping("/lector/list")
	public String getLectores(Model model) {
		model.addAttribute("lectores", lectorService.listar());
		return "lector/list";
	}

	@GetMapping("/lector/details/{id}")
	public String getUserDetails(@PathVariable Long id, Model model) {
		model.addAttribute("lector", lectorService.listarId(id));
		return "lector/details";
	}
	
	@GetMapping("/lector/delete/{id}")
	public String deleteLector(@PathVariable("id") long id) {
		// TODO
//		this.lectorService.delete(id);
		return "redirect:/lector/list";
	}

	@GetMapping("/lector/update/{id}")
	public String showFormUpdate(@PathVariable("id") long id, Model model) {
		// TODO
//		Lector lector = this.lectorService.listarId(id);
//		model.addAttribute("lector", lector);
		return "lector/update";
	}

	@GetMapping("/lector/add")
	public String showNewCrusoForm(Model model) {
		// TODO ESTO SE TIENE QUE IR, COPIARLO PARA EL EDIT
		Lector lector = new Lector();
		model.addAttribute("lector", lector);
		return "lector/add";
	}
	

}