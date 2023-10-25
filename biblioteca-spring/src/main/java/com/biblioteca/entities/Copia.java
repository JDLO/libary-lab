package com.biblioteca.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Copia {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column
	private EstadoCopia estado;

	// relacion con libro
	@ManyToOne(fetch = FetchType.LAZY) // lazy es carga demorada, Eager es carga temprana
	@JoinColumn(name = "fk_libro")
	private Libro libro;
	
	// relacion con prestamos--lector
	
}
