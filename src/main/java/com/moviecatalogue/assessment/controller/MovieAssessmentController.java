package com.moviecatalogue.assessment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.moviecatalogue.assessment.exception.BussinesRuleException;
import com.moviecatalogue.assessment.service.MovieAssessmentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Movie Assessment")
@RestController
@RequestMapping("/movieassessment")
public class MovieAssessmentController {


	@Autowired
	MovieAssessmentService assessmentService;
	
    @ApiOperation(value = "Return all Assessment bundled into Response", notes = "Return 204 if no data found")
    @ApiResponses(value = {
        @ApiResponse(code = 204, message = "There are not transactions"),
        @ApiResponse(code = 500, message = "Internal error"),
        @ApiResponse(code = 404, message = "Not Found Movie"),
        @ApiResponse(code = 201, message = "Created")})
	@PostMapping("/enteRating")
	public ResponseEntity<?> post(@RequestParam String idMovie, @RequestParam long rating, @RequestParam long idUser) throws BussinesRuleException {
		return assessmentService.post(idMovie, rating, idUser);
		
	}
    
    @ApiOperation(value = "Return all Assessment List", notes = "Return 204 if no data found")
    @ApiResponses(value = {
        @ApiResponse(code = 204, message = "There are not transactions"),
        @ApiResponse(code = 500, message = "Internal error"),
        @ApiResponse(code = 404, message = "Not Found Movie"),
        @ApiResponse(code = 201, message = "Created")})
	@GetMapping("getTotalMoviesRated")
	public ResponseEntity<?> list() {
		return assessmentService.list();
	}

	@GetMapping("getTotalMovies")
	public ResponseEntity<?> listMovie() {
		return ResponseEntity.ok(assessmentService.listMovie());
	}
	
	@DeleteMapping("deleteRated")
	public ResponseEntity<?> deleteRated() {
		return ResponseEntity.ok(assessmentService.listMovie());
	}
	
}
