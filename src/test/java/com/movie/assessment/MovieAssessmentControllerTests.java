package com.movie.assessment;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.moviecatalogue.assessment.controller.MovieAssessmentController;
import com.moviecatalogue.assessment.dto.AssessmentResponse;
import com.moviecatalogue.assessment.dto.MovieImdbResponse;
import com.moviecatalogue.assessment.entities.Assessment;
import com.moviecatalogue.assessment.entities.Movie;
import com.moviecatalogue.assessment.exception.BussinesRuleException;
import com.moviecatalogue.assessment.service.MovieAssessmentImpl;
import com.moviecatalogue.assessment.service.MovieAssessmentService;

public class MovieAssessmentControllerTests{


	@Mock
	private MovieAssessmentService movieAssessmentService;
	
	@InjectMocks
	private MovieAssessmentController movieController;
	
	Assessment saveAssessment;
	AssessmentResponse invoiceToInvoiceRespose;
	List<Assessment> saveAssessmentList;
	List<AssessmentResponse> invoiceListToInvoiceResposeList;
	MovieImdbResponse movieImdbResponse = new MovieImdbResponse();
	ResponseEntity<AssessmentResponse> invoiceToInvoiceResposeRs;
	ResponseEntity<List<AssessmentResponse>> invoiceToInvoiceResposeListRs;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		saveAssessment = new Assessment();  
		saveAssessment.setId_usuario("1");
		movieImdbResponse.setActors("armod");
		movieImdbResponse.setAwards("12");
		movieImdbResponse.setBoxOffice("1");
		movieImdbResponse.setImdbID("1");
		movieImdbResponse.setTitle("titulo");
		movieImdbResponse.setPlot("categoria");
		movieImdbResponse.setPoster("url_imagen");
		Movie movie = new Movie();
		movie.setDescripcion(movieImdbResponse.getPlot());
		movie.setId_pelicula("0");
		movie.setNombre(movieImdbResponse.getTitle());
		movie.setUrl_imagen(movieImdbResponse.getPoster());
		saveAssessment.setMovie(movie);
		saveAssessment.setNota(2);
		saveAssessment.setId_usuario("2");
		saveAssessmentList = new ArrayList();
		saveAssessmentList.add(saveAssessment);
		invoiceToInvoiceRespose = new AssessmentResponse();
		invoiceListToInvoiceResposeList = new ArrayList();
		invoiceListToInvoiceResposeList.add(invoiceToInvoiceRespose);
		invoiceToInvoiceResposeRs = new ResponseEntity<AssessmentResponse>(invoiceToInvoiceRespose, HttpStatus.OK);
		invoiceToInvoiceResposeListRs = new ResponseEntity<List<AssessmentResponse>>(invoiceListToInvoiceResposeList, HttpStatus.OK);
	}
	
	@Test
	public void put() throws BussinesRuleException {
		when(movieAssessmentService.updateById(Mockito.anyLong(),Mockito.any(Assessment.class))).thenReturn(invoiceToInvoiceResposeRs);
		Assertions.assertEquals(movieController.put(1, saveAssessment),invoiceToInvoiceResposeRs);
	}
	
	@Test
	public void post() throws BussinesRuleException {
		when(movieAssessmentService.post(Mockito.anyString(), Mockito.anyLong(),Mockito.anyString())).thenReturn(invoiceToInvoiceResposeRs);
		Assertions.assertEquals(movieController.post("1",1,"1"),invoiceToInvoiceResposeRs);
	}
	
	@Test
	public void list() throws BussinesRuleException {
		when(movieAssessmentService.list(null)).thenReturn(invoiceToInvoiceResposeListRs);
		Assertions.assertEquals(movieController.list(null),invoiceToInvoiceResposeListRs);
	}
	
	@Test
	public void delete() throws BussinesRuleException {
		when(movieAssessmentService.deleteById(Mockito.anyLong())).thenReturn(invoiceToInvoiceResposeRs);
		Assertions.assertEquals(movieController.deleteRated(1),invoiceToInvoiceResposeRs);
	}
}
