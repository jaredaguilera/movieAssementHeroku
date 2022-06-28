package com.moviecatalogue.assessment.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "This model represent a Rs Movie data that user should send on a request on post method" )
public class MovieResponse implements Serializable {
	

	private static final long serialVersionUID = -2855170449575460962L;
	
	@ApiModelProperty(name = "id_pelicula", required = true,example = "tt234234",value = "Unique Id of id_pelicula after it's created")
	private long id_pelicula;
	
	@ApiModelProperty(name = "nombre", required = true,example = "yes, sr",value = "name of Movie after it's created")
	private String nombre;
	
	@ApiModelProperty(name = "url_imagen", required = true,example = "https://www.images.cl/url",value = "url_imagen of Movie after it's created")
	private String url_imagen;
	
	@ApiModelProperty(name = "descripcion", required = true,example = "yes, sr is movie very funny",value = "descripcion of Movie after it's created")
	private String descripcion;
	
	@ApiModelProperty(name = "categoria", required = true,example = "Thriller",value = "categoria of Movie after it's created")
	private String categoria;
	
	
	public MovieResponse() {
		super();
	}


	public MovieResponse(long id_pelicula, String nombre, String url_imagen, String descripcion, String categoria) {
		super();
		this.id_pelicula = id_pelicula;
		this.nombre = nombre;
		this.url_imagen = url_imagen;
		this.descripcion = descripcion;
		this.categoria = categoria;
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
