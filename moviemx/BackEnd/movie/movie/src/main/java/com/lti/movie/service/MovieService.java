package com.lti.movie.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.lti.movie.document.Movie;

@Service
public interface MovieService
{
	public Movie addMovie(Movie movie );
	
	public Optional<Movie> updateMovie(Movie movie,String id );
	
	public void deleteMovie(String id );
	
	public Optional<Movie> getMovieById(String id);
	
	public Optional<Movie> getMovieByName(String name);
	
	public List<Movie> getAllMovies();

}
