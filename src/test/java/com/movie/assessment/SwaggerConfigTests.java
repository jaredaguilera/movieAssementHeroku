package com.movie.assessment;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.moviecatalogue.assessment.MovieAssessmentApplication;

public class SwaggerConfigTests {

    @Test
    public void testConfiguration() throws Exception {
    	MovieAssessmentApplication swaggerConfig = new MovieAssessmentApplication();
        Assertions.assertNotNull(swaggerConfig.petApi());
    }
    
    @Test
    public void testConfigurationRest() throws Exception {
    	MovieAssessmentApplication swaggerConfig = new MovieAssessmentApplication();
        Assertions.assertNotNull(swaggerConfig.getRestTemplate());
    }
    
    @Test
    public void main() {
    	//TODO
    	//MovieAssessmentApplication.main(new String[] {});
    }
    
    
}
