package com.biblioteca.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CopiaController {

	//TODO TE FALTA PONER UN DIALOGO CUANDO VAYAS A ELIMINAR A UN LECTOR
	
	@GetMapping("/copia/solicitar")
	public String getSolicitarCopia() {
		return "copia/solicitar";
	}
	
	
}
