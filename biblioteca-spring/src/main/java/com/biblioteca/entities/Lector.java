package com.biblioteca.entities;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

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
	
	// Lo siguiente puede ser util para establecer un maximo de copias (3)
//	@OneToMany(mappedBy = "lector", cascade=CascadeType.PERSIST)
//	@Size(min=1, max=3)
//	private List<Copia> copias;

	// relacion con multa
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "lector_id", referencedColumnName = "nSocio")
	private Multa multa;

	public void devolver(long id, LocalDate date) {
		// precondicion -- prestamos.notEmpty()
	}

	public void prestar(long id, LocalDate date) {
		// precondicion -- multa==0
	}

	private void multar(int dias) {

	}
}
