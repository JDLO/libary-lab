package com.biblioteca.entities;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Prestamo {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column
	private LocalDate inicio;

	@Column
	private LocalDate fin;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY) // lazy es carga demorada, Eager es carga temprana
	@JoinColumn(name = "fk_lector")
	private Lector lector;
	
	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY) // lazy es carga demorada, Eager es carga temprana
	@JoinColumn(name = "fk_copia")
	private Copia copia;

	public Prestamo() {

	}

	public Prestamo(LocalDate inicio, LocalDate fin) {
		super();
		this.inicio = inicio;
		this.fin = fin;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDate getInicio() {
		return inicio;
	}

	public void setInicio(LocalDate inicio) {
		this.inicio = inicio;
	}

	public LocalDate getFin() {
		return fin;
	}

	public void setFin(LocalDate fin) {
		this.fin = fin;
	}

	public Lector getLector() {
		return lector;
	}

	public void setLector(Lector lector) {
		this.lector = lector;
	}

	public Copia getCopia() {
		return copia;
	}

	public void setCopia(Copia copia) {
		this.copia = copia;
	}
	
	public LocalDate getFechaExp() {
		return inicio.plusDays(30);
	}
}
