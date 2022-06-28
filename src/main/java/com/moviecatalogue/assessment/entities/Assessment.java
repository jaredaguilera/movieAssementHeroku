package com.moviecatalogue.assessment.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Assessment implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1180316022329539171L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id_valoracion;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_pelicula")
	private Movie movie;
	private long id_usuario;
	private long nota;
	
	public Assessment() {
		super();
	}

	public Assessment(long id_valoracion, Movie movie, long id_usuario, long nota) {
		super();
		this.id_valoracion = id_valoracion;
		this.movie = movie;
		this.id_usuario = id_usuario;
		this.nota = nota;
	}
	
	public long getId_valoracion() {
		return id_valoracion;
	}
	public void setId_valoracion(long id_valoracion) {
		this.id_valoracion = id_valoracion;
	}
	public Movie getMovie() {
		return movie;
	}
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	public long getId_usuario() {
		return id_usuario;
	}
	public void setId_usuario(long id_usuario) {
		this.id_usuario = id_usuario;
	}
	public long getNota() {
		return nota;
	}
	public void setNota(long nota) {
		this.nota = nota;
	}

	
	
}