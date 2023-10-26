package com.biblioteca.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.biblioteca.entities.User;
import com.biblioteca.services.UserService;

@Component
public class AddAdminFormValidator implements Validator {

	@Autowired
	private UserService usersService;

	@Override
	public boolean supports(Class<?> aClass) {
		return User.class.equals(aClass);
	}

	/**
	 * Comprueba que los datos utilizados para agregar un nuevo usuario al sistema
	 * estén correctamente. De no ser así, introduce los errores detectados en el
	 * objeto de tipo Errors.
	 */
	@Override
	public void validate(Object target, Errors errors) {
		User user = (User) target;

		// Comprobar el correo
		if (!user.getEmail().contains("@")) { // Comprobar el dominio
			errors.rejectValue("email", "Error.user.email.badFormat");
		} else {
			// Comprobar correo repetido
			User userWithSameEmail = usersService.getUserByEmail(user.getEmail());
			if (userWithSameEmail != null) {
				errors.rejectValue("email", "Error.user.email.duplicate");
			}
		}

		// Comprobar el password
		if (user.getPassword() == null || user.getPassword().isBlank() || user.getPassword().isEmpty()) {
			errors.rejectValue("password", "Error.user.password.blank");
		}
	}
}
