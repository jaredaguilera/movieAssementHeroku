package com.moviecatalogue.assessment.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "This model represent a Rs Assesment data that user should send on a request on post method" )
public class AssessmentResponse implements Serializable {

	private static final long serialVersionUID = -1180316022329539171L;
	
	@ApiModelProperty(name = "id_valoracion", required = true,example = "tt234234",value = "Unique Id of id_pelicula after it's created")
	private long id_valoracion;
	
	@ApiModelProperty(name = "movie", required = true,example = "tt234234",value = "movie of Assessment after it's created")
	private MovieResponse movie;
	
	@ApiModelProperty(name = "id_usuario", required = true,example = "tt234234",value = " id_usuario of Assessment after it's created")
	private long id_usuario;
	
	@ApiModelProperty(name = "nota", required = true,example = "343",value = "nota of Assessment after it's created")
	private long nota;
	
	public AssessmentResponse() {
		super();
	}

	public AssessmentResponse(long id_valoracion, MovieResponse movie, long id_usuario, long nota) {
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
	public MovieResponse getMovie() {
		return movie;
	}
	public void setMovie(MovieResponse movie) {
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