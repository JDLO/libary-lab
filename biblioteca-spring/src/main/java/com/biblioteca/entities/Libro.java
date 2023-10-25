package com.biblioteca.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Libro {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column
	private String titulo;

	@Column
	private TipoLibro tipoLibro;
	
	@Column
	private int anyo;
	
	//relacion con copia
	
	
	//relacion con autor
}
