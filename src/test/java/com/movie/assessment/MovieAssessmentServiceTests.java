package com.movie.assessment;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.moviecatalogue.assessment.common.InvoiceResponseMapper;
import com.moviecatalogue.assessment.dto.AssessmentResponse;
import com.moviecatalogue.assessment.dto.MovieImdbResponse;
import com.moviecatalogue.assessment.dto.MovieResponse;
import com.moviecatalogue.assessment.entities.Assessment;
import com.moviecatalogue.assessment.entities.Movie;
import com.moviecatalogue.assessment.exception.BussinesRuleException;
import com.moviecatalogue.assessment.repository.AssessmentRepository;
import com.moviecatalogue.assessment.service.MovieAssessmentImpl;
import org.hamcrest.Matchers;

class MovieAssessmentServiceTests {
    
	@Mock
	private AssessmentRepository assessmentRepository;
	
	@Mock
	private InvoiceResponseMapper irspm;
	
	@Mock
	private RestTemplate restTemplate;
	    
	@InjectMocks
	private MovieAssessmentImpl movieAssessmentImpl;
	
	Assessment saveAssessment;
	AssessmentResponse invoiceToInvoiceRespose;
	List<Assessment> saveAssessmentList;
	List<AssessmentResponse> invoiceListToInvoiceResposeList;
	MovieImdbResponse movieImdbResponse = new MovieImdbResponse();
	
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
		movie.setId_pelicula(0);
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

	}
	
	@Test
	public void updateById() throws BussinesRuleException {
		MockitoAnnotations.openMocks(this);
		when(assessmentRepository.findById((long) 1)).thenReturn(Optional.of(saveAssessment));
		when(assessmentRepository.save(saveAssessment)).thenReturn(saveAssessment);
		when(irspm.InvoiceToInvoiceAssessmentRespose(saveAssessment)).thenReturn(invoiceToInvoiceRespose);
		assertEquals(movieAssessmentImpl.updateById(1,saveAssessment),ResponseEntity.ok(invoiceToInvoiceRespose));
	}
	
	@Test
	public void updateByIdException() throws BussinesRuleException {
		MockitoAnnotations.openMocks(this);
		when(assessmentRepository.findById((long) 1)).thenThrow(NullPointerException.class);
		Assertions.assertThrows(BussinesRuleException.class, ()-> movieAssessmentImpl.updateById(1,saveAssessment));
	}
	
	@Test
	public void testdeleteById() throws BussinesRuleException {
		MockitoAnnotations.openMocks(this);
		when(assessmentRepository.findAll()).thenReturn(saveAssessmentList);
		when(irspm.InvoiceToInvoiceAssessmentRespose(saveAssessment)).thenReturn(invoiceToInvoiceRespose);
		assertEquals(movieAssessmentImpl.deleteById(0),ResponseEntity.ok(invoiceToInvoiceRespose));
	}
	
	@Test
	public void testdeleteByIdListNull() throws BussinesRuleException {
		MockitoAnnotations.openMocks(this);
		saveAssessmentList.get(0).setId_valoracion(23);
		when(assessmentRepository.findAll()).thenReturn(saveAssessmentList);
		when(irspm.InvoiceToInvoiceAssessmentRespose(saveAssessment)).thenReturn(invoiceToInvoiceRespose);
		AssessmentResponse invoiceToInvoiceRespose = new AssessmentResponse();
		invoiceToInvoiceRespose.setId_usuario("0");
		invoiceToInvoiceRespose.setMovie(null);
		invoiceToInvoiceRespose.setId_valoracion(0);
		invoiceToInvoiceRespose.setNota(0);
		assertEquals(movieAssessmentImpl.deleteById(0).getBody().getNota(),ResponseEntity.ok(invoiceToInvoiceRespose).getBody().getNota());
	}
	
	@Test
	public void testDeleteByIdException() throws BussinesRuleException {
		MockitoAnnotations.openMocks(this);
		when(assessmentRepository.findAll()).thenThrow(NullPointerException.class);
		Assertions.assertThrows(BussinesRuleException.class, ()-> movieAssessmentImpl.deleteById(1));
	}
	
	@Test
	public void testList() throws BussinesRuleException {
		MockitoAnnotations.openMocks(this);
		when(assessmentRepository.findAll()).thenReturn(saveAssessmentList);
		when(irspm.InvoiceListToInvoiceAssessmentResposeList(saveAssessmentList)).thenReturn(invoiceListToInvoiceResposeList);
		assertEquals(movieAssessmentImpl.list(null),ResponseEntity.ok(invoiceListToInvoiceResposeList));
	}
	
	@Test
	public void testPost() throws BussinesRuleException, InterruptedException {
	    MockitoAnnotations.openMocks(this);
	    HttpHeaders headers = new HttpHeaders();
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    headers.setBasicAuth("admin", "admin");
	    HttpEntity <MovieImdbResponse> entity = new HttpEntity<MovieImdbResponse>(headers);
	  
	    String urlTemplate = UriComponentsBuilder.fromHttpUrl("https://moviedataimdbheroku.herokuapp.com/movieImdb/getIdParameter")
		        .queryParam("i", "{idMovie}")
		        .queryParam("plot", "full")
		        .encode()
		        .toUriString();
	    Map<String, String> params = new HashMap<>();
	    params.put("idMovie", "1");
	    ResponseEntity<MovieImdbResponse> movieResponse = new ResponseEntity<MovieImdbResponse>(movieImdbResponse ,headers,HttpStatus.OK);
		when(restTemplate.exchange(urlTemplate, HttpMethod.GET, entity, MovieImdbResponse.class, params)).thenReturn(movieResponse);
		when(assessmentRepository.save(Mockito.any(Assessment.class))).thenReturn(saveAssessment);
		when(irspm.InvoiceToInvoiceAssessmentRespose(saveAssessment)).thenReturn(invoiceToInvoiceRespose);
	    assertEquals(movieAssessmentImpl.post("1" ,(long) 2, "2"),ResponseEntity.ok(invoiceToInvoiceRespose));
	}
	
	@Test
	public void testPostException() throws BussinesRuleException, InterruptedException {
	    MockitoAnnotations.openMocks(this);
	    HttpHeaders headers = new HttpHeaders();
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    headers.setBasicAuth("admin", "admin");
	    HttpEntity <MovieImdbResponse> entity = new HttpEntity<MovieImdbResponse>(headers);
	  
	    String urlTemplate = UriComponentsBuilder.fromHttpUrl("https://moviedataimdbheroku.herokuapp.com/movieImdb/getIdParameter")
		        .queryParam("i", "{idMovie}")
		        .queryParam("plot", "full")
		        .encode()
		        .toUriString();
	    Map<String, String> params = new HashMap<>();
	    params.put("idMovie", "1");
	    ResponseEntity<MovieImdbResponse> movieResponse = new ResponseEntity<MovieImdbResponse>(movieImdbResponse ,headers,HttpStatus.OK);
		when(restTemplate.exchange(urlTemplate, HttpMethod.GET, entity, MovieImdbResponse.class, params)).thenReturn(movieResponse);
		when(assessmentRepository.save(Mockito.any(Assessment.class))).thenThrow(NullPointerException.class);
		Assertions.assertThrows(BussinesRuleException.class, ()-> movieAssessmentImpl.post("1" ,(long) 2,"2"));
	}
	
	@Test
	public void testPostExchangeException() throws BussinesRuleException, InterruptedException {
	    MockitoAnnotations.openMocks(this);
	    HttpHeaders headers = new HttpHeaders();
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    headers.setBasicAuth("admin", "admin");
	    HttpEntity <MovieImdbResponse> entity = new HttpEntity<MovieImdbResponse>(headers);
	  
	    String urlTemplate = UriComponentsBuilder.fromHttpUrl("https://moviedataimdbheroku.herokuapp.com/movieImdb/getIdParameter")
		        .queryParam("i", "{idMovie}")
		        .queryParam("plot", "full")
		        .encode()
		        .toUriString();
	    Map<String, String> params = new HashMap<>();
	    params.put("idMovie", "1");
	    ResponseEntity<MovieImdbResponse> movieResponse = new ResponseEntity<MovieImdbResponse>(movieImdbResponse ,headers,HttpStatus.OK);
		when(restTemplate.exchange(urlTemplate, HttpMethod.GET, entity, MovieImdbResponse.class, params)).thenThrow(NullPointerException.class);
		Assertions.assertThrows(BussinesRuleException.class, ()-> movieAssessmentImpl.post("1" ,(long) 2,"2"));
	}
	

}
