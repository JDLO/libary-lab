package com.biblioteca.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.biblioteca.entities.Lector;
import com.biblioteca.services.LectorService;
import com.biblioteca.validators.UpdateLectorFormValidator;

@Controller
public class LectorController {

	@Autowired
	private LectorService lectorService;
	
	@Autowired
	private UpdateLectorFormValidator updateLectorFormValidator;
	
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
	
	@GetMapping("/lector/update/{id}")
	public String showFormUpdate(@PathVariable("id") long id, Model model) {
		Lector lector = this.lectorService.listarId(id);
		model.addAttribute("lectorToBeEdited", lector);
		model.addAttribute("lector", new Lector());
		return "lector/update";
	}
	
	@PostMapping("/lector/update")
	public String setAddUser(@Validated Lector validatedLector, BindingResult result, Model model,
			RedirectAttributes redirAttrs) {
		updateLectorFormValidator.validate(validatedLector, result);
		if (result.hasErrors()) {
			model.addAttribute("lectorToBeEdited", validatedLector);
			return "/lector/update";
		}
		// Si todo va bien, edutamos al usuario
		lectorService.agregar(validatedLector);
		redirAttrs.addFlashAttribute("lectorUpdated", true);
		return "redirect:/lector/details/" + validatedLector.getnSocio();
	}
}