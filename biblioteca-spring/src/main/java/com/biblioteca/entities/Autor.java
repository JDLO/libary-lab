package com.biblioteca.entities;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Autor {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column
	private String nombre;

	@Column
	private String nacionalidad;
	
	@Column
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate fechaNacimiento;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "autor", targetEntity = Libro.class, cascade = CascadeType.ALL)
	private List<Libro> obras;

	public Autor() {

	}

	public Autor(String nombre, String nacionalidad, LocalDate fechaNacimiento) {
		super();
		this.nombre = nombre;
		this.nacionalidad = nacionalidad;
		this.fechaNacimiento = fechaNacimiento;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public List<Libro> getObras() {
		return obras;
	}

	public void setObras(List<Libro> obras) {
		this.obras = obras;
	}
	
	

}
