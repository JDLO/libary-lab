package com.biblioteca.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.entities.User;
import com.biblioteca.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repositorio;

	@Override
	public User listarId(long id) {
		return repositorio.findById(id);
	}

	@Override
	public List<User> listar() {
		return repositorio.findAll();
	}

	@Override
	public User agregar(User u) {
		return repositorio.save(u);
	}

	@Override
	public User modificar(User user) {
		User original = repositorio.findById(user.getId());
		if (user.getEmail() != null) {
			original.setEmail(user.getEmail());
		}
		return repositorio.save(original);
	}

	@Override
	public void delete(long id) {
		User u = repositorio.findById(id);
		if (u != null) {
			repositorio.delete(u);
		}

	}
}
