package com.biblioteca.controllers;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@GetMapping("/home")
	public String getHome() {
		return "user/home";
	}
	
	@GetMapping("/login")
	public String getLogin() {
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
	
}
