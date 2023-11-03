package com.biblioteca.controllers;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
<<<<<<< Updated upstream
=======
import org.springframework.web.bind.annotation.ModelAttribute;
>>>>>>> Stashed changes
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.biblioteca.entities.Multa;
import com.biblioteca.entities.Prestamo;
import com.biblioteca.entities.User;
import com.biblioteca.services.LectorService;
import com.biblioteca.services.PrestamoService;
import com.biblioteca.services.UserService;

@Controller
public class PrestamoController {

	@Autowired
	private PrestamoService prestamoService;

	@Autowired
	private UserService userService;

	@Autowired
	private LectorService lectorService;

	@GetMapping("/lector/prestamos")
	public String getPrestamos(Model model) {
		model.addAttribute("prestamosLector", prestamoService.listarPrestamosActualesLector(getActiveUser().getId()));
		return "prestamo/list";
	}

	@PostMapping("/prestamo/devolver/{id}")
	public String setDevolverCopia(@PathVariable(name = "id") long idPrestamo, RedirectAttributes redAttrs) {
		if (prestamoService.devolver(idPrestamo) == false) {
			// Si la devolucion no ha sido posible
			redAttrs.addFlashAttribute("errorDevolver", true);
			return "redirect:/lector/prestamos";
		}

		// Si la devolucion se realiza con exito
		redAttrs.addFlashAttribute("prestamoDevuelto", true);
		return "redirect:/lector/prestamos";
	}

	@GetMapping("/prestamo/solicitar")
	public String getSolicitarCopia(Model model) {
		// Obtenemos al lector en sesión
		Long idLectorEnSesion = getActiveUser().getId();

		// Si el lector en sesion ya tiene 3 prestamos, mostrar error
		if (prestamoService.listarPrestamosActualesLector(idLectorEnSesion).size() == 3) {
			model.addAttribute("lectorConTresPrestamos", true);
		}

		// Si el lector tiene una multa actual, mostrar error
		Multa multa = lectorService.listarId(idLectorEnSesion).getMulta();
		if (multa != null && multa.getfFin().isAfter(LocalDate.now())) {
			model.addAttribute("lectorConMulta", true);
		}
		return "prestamo/solicitar";
	}

	@PostMapping("/prestamo/solicitar")
	public String setSolicitarCopia(@RequestParam(name = "isbnTitulo") String isbnTitulo, Model model,
			RedirectAttributes redAttrs) {
		Long idLectorEnSesion = getActiveUser().getId();
		Prestamo prestamoRealizado;
		try {
			Long isbn = Long.parseLong(isbnTitulo);

			// Si la busqueda es por ISBN

			// Obtenemos la copia prestada al lector
			prestamoRealizado = prestamoService.prestarByIdLibro(isbn, idLectorEnSesion);
			if (prestamoRealizado == null) {
				// Si no existen copias del libro introducido, mostrar error
				model.addAttribute("copiasNoDisponibles", true);
				return "prestamo/solicitar";
			}
		} catch (NumberFormatException e) {
			// Si la busqueda es por titulo

			// Obtenemos la copia prestada al lector
			prestamoRealizado = prestamoService.prestarByTituloLibro(isbnTitulo, idLectorEnSesion);
			if (prestamoRealizado == null) {
				// Si no existen copias del libro introducido, mostrar error
				model.addAttribute("copiasNoDisponibles", true);
				return "prestamo/solicitar";
			}
		}

		// Si se asigna una copia al lector, mostrar mensaje de exito
		redAttrs.addFlashAttribute("prestamoRealizado", true);
		return "redirect:/lector/prestamos";
	}
	
	/**
	 * Devuelve el usuario con sesión iniciada en el sistema.
	 * 
	 * @return El usuario con la sesión iniciada.
	 */
	private User getActiveUser() {
		return userService.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
	}
	
	@GetMapping("/lector/devolver/{id}")
	public String getDevolverCopia(@PathVariable("id") Long idPrestamo, Model model) {
		Prestamo prestamo = this.prestamoService.listarId(idPrestamo);
		model.addAttribute("loan", prestamo);
		return "prestamo/devolver";
	}
	
	@PostMapping("/lector/devolver")
	public String setPrestamo(@ModelAttribute("loan") Prestamo prestamo) {
		this.prestamoService.devolverLibroByPrestamo(prestamo);
		return "redirect:/lector/prestamos";
	}
}
