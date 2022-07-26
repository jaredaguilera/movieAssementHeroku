package com.moviecatalogue.assessment.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import com.moviecatalogue.assessment.dto.AssessmentResponse;
import com.moviecatalogue.assessment.entities.Assessment;
import com.moviecatalogue.assessment.exception.BussinesRuleException;

public interface MovieAssessmentService{

	ResponseEntity<AssessmentResponse> post(String idMovie, long rating, String idUser) throws BussinesRuleException;

	ResponseEntity<List<AssessmentResponse>> list(String optionalIdUser);

	ResponseEntity<AssessmentResponse> deleteById(long id) throws BussinesRuleException;

	ResponseEntity<AssessmentResponse> updateById(long id, Assessment assessment) throws BussinesRuleException;

}
