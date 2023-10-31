package com.biblioteca.controllers.rest;
 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.entities.User;
import com.biblioteca.services.UserService;
 
@RestController
@RequestMapping("rest/user")
public class UserRestController {
 
	@Autowired
	private UserService userService;
 
	@GetMapping("/")
	public String home() {
		return "home";
	}
 
	@GetMapping("/list")
	public List<User> listar() {
		return userService.listar();
	}
	@GetMapping("rest/user/{id}")
	public User listarPorId(@PathVariable(value = "id") long id) {
		return userService.listarId(id);
	}
 
	@PostMapping("rest/user/add")
	public User agregar(@RequestBody User u) {
		return userService.agregar(u);
	}
 
	@PutMapping("rest/user/edit/{id}")
	public User editar(@RequestBody User u, @PathVariable long id) {
		u.setId(id);
		return userService.modificar(u);
	}
 
	@DeleteMapping("rest/user/delete/{id}")
	public void delete(@PathVariable long id) {
		userService.delete(id);
	}
}