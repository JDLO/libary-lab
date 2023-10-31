package com.biblioteca.entities;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Lector {

	@Id
	@Column
	private long nSocio;
	@Column
	private String nombre;

	@Column
	private String telefono;

	@Column
	private String direccion;

	// relacion con prestamo
	@JsonManagedReference
	@OneToMany(mappedBy = "lector", cascade = CascadeType.ALL)
	private List<Prestamo> prestamos;

	// relacion con multa
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "multa_id", referencedColumnName = "id")
	private Multa multa;

	public Lector() {

	}

	public Lector(String nombre, String telefono, String direccion) {
		super();
		this.nombre = nombre;
		this.telefono = telefono;
		this.direccion = direccion;
	}

	public long getnSocio() {
		return nSocio;
	}

	public void setnSocio(long nSocio) {
		this.nSocio = nSocio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public List<Prestamo> getPrestamos() {
		return prestamos;
	}

	public void setPrestamos(List<Prestamo> prestamos) {
		this.prestamos = prestamos;
	}

	public Multa getMulta() {
		return multa;
	}

	public void setMulta(Multa multa) {
		this.multa = multa;
	}

	public void devolver(long id, LocalDate date) {
		// precondicion -- prestamos.notEmpty()
	}

	public void prestar(long id, LocalDate date) {
		// precondicion -- multa==0
	}

	public void multar(int dias) {

	}
}
