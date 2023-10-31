package com.biblioteca.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Libro {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column
	private String titulo;

	@Column
	@Enumerated(EnumType.ORDINAL)
	private TipoLibro tipoLibro;

	@Column
	private String editorial;

	@Column
	private int anyo;

	// relacion con copia
	@JsonManagedReference
	@OneToMany(mappedBy = "libro", targetEntity = Copia.class, cascade = CascadeType.ALL)
	private List<Copia> ejemplares;

	// relacion con autor
	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY) // lazy es carga demorada, Eager es carga temprana
	@JoinColumn(name = "fk_autor")
	private Autor autor;

	public Libro() {

	}

	public Libro(String titulo, TipoLibro tipoLibro, String editorial, int anyo) {
		super();
		this.titulo = titulo;
		this.tipoLibro = tipoLibro;
		this.editorial = editorial;
		this.anyo = anyo;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public TipoLibro getTipoLibro() {
		return tipoLibro;
	}

	public void setTipoLibro(TipoLibro tipoLibro) {
		this.tipoLibro = tipoLibro;
	}

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	public int getAnyo() {
		return anyo;
	}

	public void setAnyo(int anyo) {
		this.anyo = anyo;
	}

	public List<Copia> getEjemplares() {
		return ejemplares;
	}

	public void setEjemplares(List<Copia> ejemplares) {
		this.ejemplares = ejemplares;
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

}
