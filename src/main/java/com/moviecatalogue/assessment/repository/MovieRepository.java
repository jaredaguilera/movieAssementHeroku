package com.moviecatalogue.assessment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moviecatalogue.assessment.entities.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {

}
