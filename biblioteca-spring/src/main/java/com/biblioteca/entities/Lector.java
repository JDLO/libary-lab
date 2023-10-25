package com.biblioteca.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Lector {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long nSocio;
	@Column
	private String nombre;

	@Column
	private String telefono;

	@Column
	private String direccion;

	// relacion con prestamo--copia

	// relacion con multa

	public void devolver(long id, LocalDate date) {
		// precondicion -- prestamos.notEmpty()
	}

	public void prestar(long id, LocalDate date) {
		// precondicion -- multa==0
	}

	private void multar(int dias) {

	}
}
