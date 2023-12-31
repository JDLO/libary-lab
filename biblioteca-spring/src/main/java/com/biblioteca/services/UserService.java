package com.biblioteca.services;

import java.util.List;

import com.biblioteca.entities.User;

public interface UserService {

	User listarId(long id); // findById del repositorio

	List<User> listar(); // findAll del repo

	List<User> listarEnabled();

	User agregar(User u); // save del repo

	User modificar(User u); // save del repo

	void delete(long id); // delete(User); del repo

	User getUserByEmail(String email);

	List<User> listarAdmins();

	User disableUser(long id);

	List<User> listarDisabled();

	Object enableUser(long id);
}
