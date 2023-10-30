package com.biblioteca.services;

import org.springframework.stereotype.Service;

/**
 * Contiene los roles disponibles en el sistema.
 * 
 */
@Service
public class RolesService {
	private String[] roles = { "ADMIN", "LECTOR" };

	public String[] getRoles() {
		return roles;
	}
}