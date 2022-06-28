package com.moviecatalogue.assessment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Ratings {
	
	@JsonProperty ("Source")
	private String source;
	
	@JsonProperty ("Value")
	private String value;

	public Ratings() {
		super();
	}

	public Ratings(String source, String value) {
		super();
		this.source = source;
		this.value = value;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
}
