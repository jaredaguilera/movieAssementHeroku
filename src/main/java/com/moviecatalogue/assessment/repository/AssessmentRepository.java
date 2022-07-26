package com.moviecatalogue.assessment.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.moviecatalogue.assessment.entities.Assessment;

public interface AssessmentRepository extends JpaRepository<Assessment, Long> {
	
	@Query(value = "select a from Assessment a where id_usuario like :id_user")
	List<Assessment> findAllByUser(@Param("id_user") String id_user);
}
