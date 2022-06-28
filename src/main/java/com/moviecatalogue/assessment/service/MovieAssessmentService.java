package com.moviecatalogue.assessment.service;

import java.util.List;
import org.springframework.http.ResponseEntity;
import com.moviecatalogue.assessment.dto.AssessmentResponse;
import com.moviecatalogue.assessment.entities.Movie;
import com.moviecatalogue.assessment.exception.BussinesRuleException;

public interface MovieAssessmentService{

	ResponseEntity<AssessmentResponse> post(String idMovie, long rating, long idUser) throws BussinesRuleException;

	ResponseEntity<List<AssessmentResponse>> list();

	List<Movie> listMovie();

	void deleteById(long id);

}
