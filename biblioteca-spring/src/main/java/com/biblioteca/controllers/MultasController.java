package com.biblioteca.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.biblioteca.entities.Multa;
import com.biblioteca.services.MultaService;

@Controller
public class MultasController {

	@Autowired
	private MultaService multaService;

	@GetMapping("/multa/list")
	public String viewHomePagemulta(Model model) {
		List<Multa> listMultas = multaService.listar();
		model.addAttribute("listPenaltys", listMultas);
		return "multa/list";
	}

	@PostMapping("/multa/save/{id}")
	public String saveMulta(@PathVariable("id") Long idLector, @ModelAttribute("penalty") Multa multa) {
		multaService.agregar(multa);
		return "redirect:/multa/list";
	}

	@PostMapping("/multa/sysupdatep")
	public String updateSysMulta(Model model) {
//		boolean retorno = this.multaService.actualizarMultasSistema();
//		model.addAttribute("Save", retorno);
		return "redirect:/multa/list";
	}

	@PostMapping("/multa/delete/{id}")
	public String deleteMulta(@PathVariable("id") long id) {
		this.multaService.delete(id);
		return "redirect:/multa/list";
	}

	@GetMapping("/multa/update/{id}")
	public String showFormUpdate(@PathVariable("id") long id, Model model) {
		Multa multa = this.multaService.listarId(id);
		model.addAttribute("penalty", multa);
		return "multa/update";
	}

	@GetMapping("/multa/sysupdatep")
	public String showFormSysUpdatePenalty(Model model) {
		return "multa/multa-system-update";
	}
}
