package com.biblioteca.util;

import com.biblioteca.entities.Lector;
import com.biblioteca.entities.User;

public class Mapper {

	public static Lector userToLector(User user) {
		Lector lector = new Lector();
		lector.setnSocio(user.getId());
		String nombre = user.getEmail().split("@")[0];
		lector.setNombre(nombre);
		return lector;
	}
}