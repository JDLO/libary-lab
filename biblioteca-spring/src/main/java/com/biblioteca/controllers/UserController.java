package com.biblioteca.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.biblioteca.entities.User;
import com.biblioteca.services.UserService;
import com.biblioteca.validators.AddAdminFormValidator;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private AddAdminFormValidator addAdminFormValidator;

	
	@GetMapping("/")
	public String getIndex() {
		return "redirect:/home";
	}
	
	@GetMapping("/home")
	public String getHome() {
		return "user/home";
	}

	@GetMapping("/login")
	public String getLogin() {
		return "user/login";
	}
	
	/**
	 * Método que maneja la solicitud GET "/failure-login" para mostrar la página de
	 * inicio de sesión con un mensaje de error en caso de fallo de inicio de
	 * sesión.
	 * 
	 * @param model El modelo utilizado para pasar atributos a la vista.
	 * @return La vista "login" con un mensaje de error si el usuario no ha iniciado
	 *         sesión, o una redirección a la ruta "/home" si el usuario ya tiene
	 *         sesión iniciada.
	 */
	@GetMapping(value = "/failure-login")
	public String handleLoginFailure(Model model) {
		// Si el usuario ya tiene sesión iniciada, redirigir a la página principal
		if (getActiveUser() != null) {
			return "redirect:/home";
		}
		model.addAttribute("loginFailure", true);
		return "user/login";
	}

	@GetMapping("/admin/add")
	public String getAddAdmin(Model model) {
		model.addAttribute("user", new User());
		return "admin/add";
	}

	@PostMapping("/admin/add")
	public String setAddAdmin(@Validated User userValidated, BindingResult result, Model model,
			RedirectAttributes redirAttrs) {
		addAdminFormValidator.validate(userValidated, result);
		if (result.hasErrors()) {
			return "admin/add";
		}
		userService.agregar(userValidated);
		return "redirect:/home";
	}
	
	@GetMapping("/admin/list")
	public String getAdmins(Model model) {
		model.addAttribute("admins", userService.listarAdmins());
		model.addAttribute("activeUser", getActiveUser());
		return "admin/list";
	}
	
	/**
	 * Devuelve el usuario con sesión iniciada en el sistema.
	 * 
	 * @return El usuario con la sesión iniciada.
	 */
	private User getActiveUser() {
		return userService.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
	}
}
