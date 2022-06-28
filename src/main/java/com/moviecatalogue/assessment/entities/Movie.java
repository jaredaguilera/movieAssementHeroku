package com.moviecatalogue.assessment.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Movie implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2855170449575460962L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id_pelicula;
	private String nombre;
	private String url_imagen;
	@Column( length = 100000 )
	private String descripcion;
	private String categoria;
    @OneToOne(mappedBy = "movie")
	private Assessment assessment;
	
	public Movie(long id_pelicula, String nombre, String url_imagen, String descripcion, String categoria,
			Assessment assessment) {
		super();
		this.id_pelicula = id_pelicula;
		this.nombre = nombre;
		this.url_imagen = url_imagen;
		this.descripcion = descripcion;
		this.categoria = categoria;
		this.assessment = assessment;
	}


	public Movie() {
		super();
	}


	public Assessment getAssessment() {
		return assessment;
	}


	public void setAssessment(Assessment assessment) {
		this.assessment = assessment;
	}


	public long getId_pelicula() {
		return id_pelicula;
	}


	public void setId_pelicula(long id_pelicula) {
		this.id_pelicula = id_pelicula;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getUrl_imagen() {
		return url_imagen;
	}


	public void setUrl_imagen(String url_imagen) {
		this.url_imagen = url_imagen;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public String getCategoria() {
		return categoria;
	}


	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}	
	
	
}
