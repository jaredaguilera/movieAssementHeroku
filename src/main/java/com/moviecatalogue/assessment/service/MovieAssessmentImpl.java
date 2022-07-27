package com.moviecatalogue.assessment.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.moviecatalogue.assessment.common.InvoiceResponseMapper;
import com.moviecatalogue.assessment.dto.AssessmentResponse;
import com.moviecatalogue.assessment.dto.MovieImdbResponse;
import com.moviecatalogue.assessment.entities.Assessment;
import com.moviecatalogue.assessment.entities.Movie;
import com.moviecatalogue.assessment.exception.BussinesRuleException;
import com.moviecatalogue.assessment.repository.AssessmentRepository;
import com.moviecatalogue.assessment.repository.MovieRepository;

@Service
public class MovieAssessmentImpl implements MovieAssessmentService{
	
	//private final WebClient.Builder webClientBuilder;
	
	@Autowired
	AssessmentRepository assessmentRepository;

	@Autowired
	MovieRepository movieRepository;
	
    @Autowired(required=true)
    InvoiceResponseMapper irspm;
    
    @Autowired
    RestTemplate restTemplate;
	
	/*public MovieAssessmentImpl(WebClient.Builder webClientBuilder) {
		this.webClientBuilder = webClientBuilder;
	}*/

	// define timeout
	/*TcpClient tcpClient = TcpClient.create().option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
			.doOnConnected(connection -> {
				connection.addHandlerLast(new ReadTimeoutHandler(5000, TimeUnit.MILLISECONDS));
				connection.addHandlerLast(new WriteTimeoutHandler(5000, TimeUnit.MILLISECONDS));
			});*/

	@Override
	public ResponseEntity<AssessmentResponse> post(String idMovie, long rating, String idUser) throws BussinesRuleException {
		MovieImdbResponse movieResponse = new MovieImdbResponse();
		AssessmentResponse invoiceToInvoiceRespose = new AssessmentResponse();
		
		//movieResponse = reactivePostImdb(idMovie, movieResponse);
		
		movieResponse = restTemplatePostImdb(idMovie, movieResponse);
		
		try {
			Movie saveMovie = new Movie();
			//saveMovie.setId_pelicula(movieResponse.getImdbID());
			saveMovie.setNombre(movieResponse.getTitle());
			saveMovie.setDescripcion(movieResponse.getPlot());
			saveMovie.setUrl_imagen(movieResponse.getPoster());
			Assessment saveAssessment = new Assessment();
			saveAssessment.setMovie(saveMovie);
			saveAssessment.setNota(rating);
			saveAssessment.setId_usuario(idUser);
			saveAssessment = assessmentRepository.save(saveAssessment);
			invoiceToInvoiceRespose = irspm.InvoiceToInvoiceAssessmentRespose(saveAssessment);
		
		} catch (Exception e) {
			BussinesRuleException exception = new BussinesRuleException("504", e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
			throw exception;
		}
		
		return ResponseEntity.ok(invoiceToInvoiceRespose);
	}

	private MovieImdbResponse restTemplatePostImdb(String idMovie, MovieImdbResponse movieResponse)
			throws BussinesRuleException {
		try {
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
	      params.put("idMovie", idMovie);
	      
	      movieResponse = restTemplate.exchange(urlTemplate, HttpMethod.GET, entity, MovieImdbResponse.class, params).getBody();
		} catch (Exception e) {
			BussinesRuleException exception = new BussinesRuleException("404", "No encontrada la pelicula", HttpStatus.NOT_FOUND);
			throw exception;
		}
		return movieResponse;
	}

	/*private MovieImdbResponse reactivePostImdb(String idMovie, MovieImdbResponse movieResponse)
			throws BussinesRuleException {
		try {
			
			//llamada servicio
			WebClient client = webClientBuilder.clientConnector(new ReactorClientHttpConnector(HttpClient.from(tcpClient)))
					.baseUrl("https://moviedataimdbheroku.herokuapp.com/movieImdb")
					.defaultHeaders(header -> header.setBasicAuth("admin", "admin"))
					.defaultUriVariables(Collections.singletonMap("url", "https://moviedataimdbheroku.herokuapp.com/movieImdb")).build();
		   JsonNode block = client
					.method(HttpMethod.GET).uri(uriBuilder -> uriBuilder.path("/getIdParameter")
							.queryParam("i", idMovie).queryParam("plot", "full").build())
					.retrieve().bodyToMono(JsonNode.class).block();
			ObjectMapper objectMapper = new ObjectMapper();
			movieResponse = objectMapper.convertValue(block, MovieImdbResponse.class);
		} catch (Exception e) {
			BussinesRuleException exception = new BussinesRuleException("404", "No encontrada la pelicula", HttpStatus.NOT_FOUND);
			throw exception;
		}
		return movieResponse;
	}*/
	
	@Override
	public ResponseEntity<List<AssessmentResponse>> list(String optionalIdUser){ 
		if(optionalIdUser!=null) {
			List<Assessment> list = assessmentRepository.findAllByUser(optionalIdUser);
			List<AssessmentResponse> invoiceListToInvoiceResposeList = irspm.InvoiceListToInvoiceAssessmentResposeList(list);
			return ResponseEntity.ok(invoiceListToInvoiceResposeList);
		}else{
			List<Assessment> list = assessmentRepository.findAll();
		    List<AssessmentResponse> invoiceListToInvoiceResposeList = irspm.InvoiceListToInvoiceAssessmentResposeList(list);
		    return ResponseEntity.ok(invoiceListToInvoiceResposeList);
		}
	}
	
	
	@Override
	public ResponseEntity<AssessmentResponse> deleteById(long id) throws BussinesRuleException {
		AssessmentResponse invoiceToInvoiceRespose = new AssessmentResponse();
		try {
			List<Assessment> list = assessmentRepository.findAll();
			for (Assessment assessment : list) {
				if(assessment.getId_valoracion() == id)
				{
					Assessment saveAssessment = new Assessment();
					saveAssessment = assessment;
					assessmentRepository.deleteById(id);
					invoiceToInvoiceRespose = irspm.InvoiceToInvoiceAssessmentRespose(saveAssessment);
				}
			}
		} catch (Exception e) {
			BussinesRuleException exception = new BussinesRuleException("504", e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
			throw exception;
		}
		return ResponseEntity.ok(invoiceToInvoiceRespose);
	}

	@Override
	public ResponseEntity<AssessmentResponse> updateById(long id, Assessment assessment) throws BussinesRuleException {
		AssessmentResponse invoiceToInvoiceRespose = new AssessmentResponse();
		try {
			Assessment saveAssessment = new Assessment();    
			saveAssessment = assessmentRepository.findById(id).get();
			saveAssessment.setNota(assessment.getNota());
			saveAssessment = assessmentRepository.save(saveAssessment);
			invoiceToInvoiceRespose = irspm.InvoiceToInvoiceAssessmentRespose(saveAssessment);
		} catch (Exception e) {
			BussinesRuleException exception = new BussinesRuleException("504", e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
			throw exception;
		}
		return ResponseEntity.ok(invoiceToInvoiceRespose);
	}
}
