package com.biblioteca.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.biblioteca.entities.User;
import com.biblioteca.repositories.UserRepository;
import com.biblioteca.util.Mapper;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repositorio;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private LectorService lectorService;

	@Override
	public User listarId(long id) {
		return repositorio.findById(id);
	}

	@Override
	public List<User> listar() {
		return repositorio.findAll();
	}

	@Override
	public User agregar(User user) {
		if (user == null) {
			throw new IllegalArgumentException("El usuario no puede ser null");
		}
		// Pasamos el correo electrónico a minúsculas
		user.setEmail(user.getEmail().toLowerCase());

		// Se encripta la contraseña y se guarda en el objeto user
		user.setPassword(encoder.encode(user.getPassword()));

		repositorio.save(user);

		// Si el usuario tiene rol lector
		if (user.getRole().equals("LECTOR")) {
			lectorService.agregar(Mapper.userToLector(user));
		}
		return user;
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
			// Si el usuario es de tipo lector, se elimina
			if (u.getRole().equals("LECTOR")) {
				lectorService.delete(id);
			}
			repositorio.delete(u);
		}
	}

	@Override
	public User getUserByEmail(String email) {
		return repositorio.findByEmail(email);
	}

	@Override
	public List<User> listarAdmins() {
		return repositorio.findAdmins();
	}
}
