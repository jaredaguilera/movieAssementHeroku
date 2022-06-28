package com.moviecatalogue.assessment.common;


import java.util.List;
import org.mapstruct.Mapper;
import com.moviecatalogue.assessment.dto.AssessmentResponse;
import com.moviecatalogue.assessment.dto.MovieResponse;
import com.moviecatalogue.assessment.entities.Assessment;
import com.moviecatalogue.assessment.entities.Movie;

@Mapper(componentModel = "spring")
public interface InvoiceResponseMapper {
    
      /*@Mappings({
  @Mapping(source = "customerId", target = "customer"),
  @Mapping(source = "id", target = "invoiceId")})*/
  MovieResponse InvoiceToInvoiceMovieRespose(Movie source);  
  
  AssessmentResponse InvoiceToInvoiceAssessmentRespose(Assessment source);  
  
  List<MovieResponse> InvoiceListToInvoiceMovieResposeList(List<Movie> source);    

  List<AssessmentResponse> InvoiceListToInvoiceAssessmentResposeList(List<Assessment> source);  
  
/*  @InheritInverseConfiguration
  Invoice InvoiceResponseToInvoice(InvoiceResponse srr);
  
  @InheritInverseConfiguration
  List<Invoice> InvoiceResponseToInvoiceList(List<InvoiceResponse> source);  */  
    
}
