package com.biblioteca.validators;

import org.apache.commons.exec.util.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.biblioteca.entities.Lector;

@Component
public class UpdateLectorFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> aClass) {
		return Lector.class.equals(aClass);
	}

	/**
	 * Comprueba que los datos utilizados para editar un lector estén correctamente.
	 * De no ser así, introduce los errores detectados en el objeto de tipo Errors.
	 */
	@Override
	public void validate(Object target, Errors errors) {
		Lector lector = (Lector) target;

		// Comprobar el nombre
		if (lector.getNombre() == null || lector.getNombre().isEmpty() || lector.getNombre().isBlank()) {
			errors.rejectValue("nombre", "Error.lector.nombre.empty");
		}

		//Comprobar el numero
		if (lector.getTelefono() != null && !lector.getTelefono().isEmpty())
			try {
				Double.parseDouble(lector.getTelefono());
			} catch (NumberFormatException nfe) {
				errors.rejectValue("telefono", "Error.lector.telefono.notNumber");
			}
	}
}
