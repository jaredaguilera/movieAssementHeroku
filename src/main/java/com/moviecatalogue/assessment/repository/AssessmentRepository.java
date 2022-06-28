package com.moviecatalogue.assessment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moviecatalogue.assessment.entities.Assessment;

public interface AssessmentRepository extends JpaRepository<Assessment, Long> {
	
}
