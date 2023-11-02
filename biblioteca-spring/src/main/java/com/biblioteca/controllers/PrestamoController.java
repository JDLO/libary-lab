package com.biblioteca.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.biblioteca.entities.Copia;
import com.biblioteca.entities.Lector;
import com.biblioteca.entities.Prestamo;
import com.biblioteca.entities.User;
import com.biblioteca.services.CopiaService;
import com.biblioteca.services.LectorService;
import com.biblioteca.services.LibroService;
import com.biblioteca.services.PrestamoService;
import com.biblioteca.services.UserService;

@Controller
public class PrestamoController {

	@Autowired
	private PrestamoService prestamoService;
	
	@Autowired
	private UserService userService;

	@GetMapping("/prestamo/solicitar")
	public String getSolicitarCopia() {
		return "prestamo/solicitar";
	}

	@PostMapping("/prestamo/solicitar")
	public String setSolicitarCopia(@RequestParam(name = "isbnTitulo") String isbnTitulo, RedirectAttributes redAttrs) {
		Long isbn;
		Prestamo prestamoRealizado;
		try {
			isbn = Long.parseLong(isbnTitulo);

			// Si no salta la excepcion, es un isbn
			prestamoRealizado = prestamoService.prestarByLibroId(isbn, getActiveUser().getId());
			if (prestamoRealizado == null) {
				redAttrs.addFlashAttribute("prestamoNoRealizado", true);
			}

		} catch (NumberFormatException e) {
			//TODO falta hacer esto
			// Si salta la excepcion, es un titulo
//			prestamoRealizado = copiaService.listarByTituloLibro(isbnTitulo);
		}

		//TODO falta agregar mensajes de exito
		return "redirect:/lector/prestamos";
	}
	
	@GetMapping("/lector/prestamos")
	public String getPrestamos(Model model) {
		model.addAttribute("prestamosLector", prestamoService.listarPrestamosDeLector(getActiveUser().getId()));
		return "prestamo/list";
	}

	//TODO preguntarle quién es el que devuelve el libro, si es el administrador o el lector
	
	/**
	 * Devuelve el usuario con sesión iniciada en el sistema.
	 * 
	 * @return El usuario con la sesión iniciada.
	 */
	private User getActiveUser() {
		return userService.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
	}

}
