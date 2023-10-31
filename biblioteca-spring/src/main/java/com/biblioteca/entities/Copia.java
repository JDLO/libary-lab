package com.biblioteca.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Copia {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column
	private EstadoCopia estado;

	// relacion con libro
	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY) // lazy es carga demorada, Eager es carga temprana
	@JoinColumn(name = "fk_libro")
	private Libro libro;

	// relacion con prestamo
	@JsonManagedReference
	@OneToMany(mappedBy = "copia", cascade = CascadeType.ALL)
	private List<Prestamo> prestamos;

	public Copia() {

	}

	public Copia(EstadoCopia estado) {
		super();
		this.estado = estado;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public EstadoCopia getEstado() {
		return estado;
	}

	public void setEstado(EstadoCopia estado) {
		this.estado = estado;
	}

	public Libro getLibro() {
		return libro;
	}

	public void setLibro(Libro libro) {
		this.libro = libro;
	}

	public List<Prestamo> getPrestamos() {
		return prestamos;
	}

	public void setPrestamos(List<Prestamo> prestamos) {
		this.prestamos = prestamos;
	}

	public void addPrestamo(Prestamo prestamo) {
		this.prestamos.add(prestamo);
	}

	public void deletePrestamo(Prestamo prestamo) {
		this.prestamos.remove(prestamo);
	}

}
