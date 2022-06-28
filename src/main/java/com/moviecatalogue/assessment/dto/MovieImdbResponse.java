package com.moviecatalogue.assessment.dto;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "This model represent a Invoice data that user should send on a request on post method" )
public class MovieImdbResponse {

	@JsonProperty ("Title")
	private String title;
	
	@JsonProperty ("Year")
	private String year;
	
	@JsonProperty ("Rated")
	private String rated;
	
	@JsonProperty ("Released")
	private String released;
	
	@JsonProperty ("Runtime")
	private String runtime;
	
	@JsonProperty ("Genre")
	private String genre;
	
	@JsonProperty ("Director")
	private String director;
	
	@JsonProperty ("Writer")
	private String writer;
	
	@JsonProperty ("Actors")
	private String actors;
	
	@JsonProperty ("Plot")
	private String plot;
	
	@JsonProperty ("Language")
	private String language;
	
	@JsonProperty ("Country")
	private String country;
	
	@JsonProperty ("Awards")
	private String awards;
	
	@JsonProperty ("Poster")
	private String poster;
	
	@JsonProperty ("Ratings")
	private List<Ratings> ratings;
	
	@JsonProperty ("Metascore")
	private String metascore;
	
	@JsonProperty ("imdbRating")
	private String imdbRating;
	
	@JsonProperty ("imdbVotes")
	private String imdbVotes;
	
	@JsonProperty ("imdbID")
	private String imdbID;
	
	@JsonProperty ("Type")
	private String type;
	
	@JsonProperty ("DVD")
	private String dvd;
	
	@JsonProperty ("BoxOffice")
	private String boxOffice;
	
	@JsonProperty ("Production")
	private String production;
	
	@JsonProperty ("Website")
	private String website;
	
	@JsonProperty ("Response")
	private Boolean response;

	public MovieImdbResponse(){
		
	}
	
	public MovieImdbResponse(String title, String year, String rated, String released, String runtime, String genre,
			String director, String writer, String actors, String plot, String language, String country, String awards,
			String poster, List<Ratings> ratings, String metascore, String imdbRating, String imdbVotes, String imdbID,
			String type, String dvd, String boxOffice, String production, String website, Boolean response) {
		super();
		this.title = title;
		this.year = year;
		this.rated = rated;
		this.released = released;
		this.runtime = runtime;
		this.genre = genre;
		this.director = director;
		this.writer = writer;
		this.actors = actors;
		this.plot = plot;
		this.language = language;
		this.country = country;
		this.awards = awards;
		this.poster = poster;
		this.ratings = ratings;
		this.metascore = metascore;
		this.imdbRating = imdbRating;
		this.imdbVotes = imdbVotes;
		this.imdbID = imdbID;
		this.type = type;
		this.dvd = dvd;
		this.boxOffice = boxOffice;
		this.production = production;
		this.website = website;
		this.response = response;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getRated() {
		return rated;
	}

	public void setRated(String rated) {
		this.rated = rated;
	}

	public String getReleased() {
		return released;
	}

	public void setReleased(String released) {
		this.released = released;
	}

	public String getRuntime() {
		return runtime;
	}

	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getActors() {
		return actors;
	}

	public void setActors(String actors) {
		this.actors = actors;
	}

	public String getPlot() {
		return plot;
	}

	public void setPlot(String plot) {
		this.plot = plot;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getAwards() {
		return awards;
	}

	public void setAwards(String awards) {
		this.awards = awards;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public List<Ratings> getRatings() {
		return ratings;
	}

	public void setRatings(List<Ratings> ratings) {
		this.ratings = ratings;
	}

	public String getMetascore() {
		return metascore;
	}

	public void setMetascore(String metascore) {
		this.metascore = metascore;
	}

	public String getImdbRating() {
		return imdbRating;
	}

	public void setImdbRating(String imdbRating) {
		this.imdbRating = imdbRating;
	}

	public String getImdbVotes() {
		return imdbVotes;
	}

	public void setImdbVotes(String imdbVotes) {
		this.imdbVotes = imdbVotes;
	}

	public String getImdbID() {
		return imdbID;
	}

	public void setImdbID(String imdbID) {
		this.imdbID = imdbID;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDvd() {
		return dvd;
	}

	public void setDvd(String dvd) {
		this.dvd = dvd;
	}

	public String getBoxOffice() {
		return boxOffice;
	}

	public void setBoxOffice(String boxOffice) {
		this.boxOffice = boxOffice;
	}

	public String getProduction() {
		return production;
	}

	public void setProduction(String production) {
		this.production = production;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public Boolean getResponse() {
		return response;
	}

	public void setResponse(Boolean response) {
		this.response = response;
	}

	
	
}
