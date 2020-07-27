package com.lti.movie.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.lti.movie.dao.MovieRepository;
import com.lti.movie.document.Movie;
import com.lti.movie.dto.MovieDTO;
import com.lti.movie.exception.ResourceNotFoundException;

@Service
public class MovieServiceImpl implements MovieService 
{
	MovieRepository movieRepository;
	
	MovieServiceImpl(MovieRepository movieRepository)
	{
		this.movieRepository=movieRepository;
	}

	@Override
	public Movie addMovie(Movie movie) 
	{
		Movie movieObj=movieRepository.save(movie);
		
		return movieObj;
		
	}
	
	@Override
	public Optional<Movie> updateMovie(Movie movie,String id) 
	{
		return Optional.of(movieRepository.findById(id).map(mv->
		{
			mv.setName(movie.getName());
			mv.setCategory(movie.getCategory());
			mv.setDirector(movie.getDirector());
			mv.setProducer(movie.getProducer());
			return movieRepository.save(mv);
		})
		.orElseThrow(()-> 
		{
		    //movie.setId(id);   
	        //return movieRepository.save(movie);
			throw new  ResourceNotFoundException("movie not found in db");
		}));
		
	}

	@Override
	public void deleteMovie(String id) 
	{
	
		movieRepository.deleteById(id);
		
	}

	@Override
	public Optional<Movie> getMovieById(String id) 
	{
		Optional<Movie> movie=movieRepository.findById(id);
		return movie;
	}

	
	@Override
	public Optional<Movie> getMovieByName(String name) {
		Optional<Movie>movieOb =movieRepository.findByName(name);
		return movieOb;
	
	}

	@Override
	public List<Movie> getAllMovies() {
		
	 return movieRepository.findAll();
	}
	}
