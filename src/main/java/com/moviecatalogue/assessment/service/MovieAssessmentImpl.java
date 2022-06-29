package com.moviecatalogue.assessment.service;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moviecatalogue.assessment.common.InvoiceResponseMapper;
import com.moviecatalogue.assessment.dto.AssessmentResponse;
import com.moviecatalogue.assessment.dto.MovieImdbResponse;
import com.moviecatalogue.assessment.entities.Assessment;
import com.moviecatalogue.assessment.entities.Movie;
import com.moviecatalogue.assessment.exception.BussinesRuleException;
import com.moviecatalogue.assessment.repository.AssessmentRepository;
import com.moviecatalogue.assessment.repository.MovieRepository;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.TcpClient;

@Service
public class MovieAssessmentImpl implements MovieAssessmentService{
	
	private final WebClient.Builder webClientBuilder;
	
	@Autowired
	AssessmentRepository assessmentRepository;

	@Autowired
	MovieRepository movieRepository;
	
    @Autowired(required=true)
    InvoiceResponseMapper irspm;
	
	public MovieAssessmentImpl(WebClient.Builder webClientBuilder) {
		this.webClientBuilder = webClientBuilder;
	}

	// define timeout
	TcpClient tcpClient = TcpClient.create().option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
			.doOnConnected(connection -> {
				connection.addHandlerLast(new ReadTimeoutHandler(5000, TimeUnit.MILLISECONDS));
				connection.addHandlerLast(new WriteTimeoutHandler(5000, TimeUnit.MILLISECONDS));
			});

	@Override
	public ResponseEntity<AssessmentResponse> post(String idMovie, long rating, long idUser) throws BussinesRuleException {
		MovieImdbResponse movieResponse = new MovieImdbResponse();
		AssessmentResponse invoiceToInvoiceRespose = new AssessmentResponse();
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
		
	
		try {
			Movie saveMovie = new Movie();
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
	
	@Override
	public ResponseEntity<List<AssessmentResponse>> list(){ 
		List<Assessment> list = assessmentRepository.findAll();
	    List<AssessmentResponse> InvoiceListToInvoiceResposeList = irspm.InvoiceListToInvoiceAssessmentResposeList(list);
	    return ResponseEntity.ok(InvoiceListToInvoiceResposeList);
	}
	
	@Override
	public List<Movie> listMovie(){ 
		return movieRepository.findAll();
	}
	
	
	@Override
	public void deleteById(long id){ 
		assessmentRepository.deleteById(id);
	}
}
