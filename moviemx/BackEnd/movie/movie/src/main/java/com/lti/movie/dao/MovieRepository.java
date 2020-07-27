package com.lti.movie.dao;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.lti.movie.document.Movie;

@Repository
public interface MovieRepository extends MongoRepository<Movie, String>
{
	public Optional<Movie> findByName(String name);
	
}
