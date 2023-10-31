package com.biblioteca.controllers.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.biblioteca.entities.Lector;
import com.biblioteca.services.LectorService;
import com.biblioteca.validators.UpdateLectorFormValidator;

@RestController
@RequestMapping("/rest/lector")
public class LectorRestController {

	@Autowired
	private LectorService lectorService;
	
	@Autowired
	private UpdateLectorFormValidator updateLectorFormValidator;
	
	@GetMapping("/list")
	public List<Lector> getLectores() {		
		return lectorService.listar();
	}

	@GetMapping("/details/{id}")
	public Lector getUserDetails(@PathVariable Long id,@RequestBody Lector model) {
		return lectorService.listarId(id);
	}
	
	@GetMapping("/delete/{id}")
	public void deleteLector(@PathVariable("id") long id) {
		// TODO
//		this.lectorService.delete(id);
	}

	@GetMapping("/update/{id}")
	public Lector showFormUpdate(@PathVariable("id") long id, @RequestBody Lector model) {
		Lector lector = this.lectorService.listarId(id);
		return lectorService.agregar(lector);
	}
	
	@PostMapping("/update")
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