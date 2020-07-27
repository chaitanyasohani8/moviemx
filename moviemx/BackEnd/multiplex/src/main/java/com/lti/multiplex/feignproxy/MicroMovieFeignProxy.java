package com.lti.multiplex.feignproxy;

import java.util.List;
import java.util.Optional;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import com.lti.multiplex.dto.MovieDTO;

@FeignClient(name="movie-service")
@RibbonClient(name="movie-service")

public interface MicroMovieFeignProxy 
{
	@GetMapping("movies/getmoviebyid/{id}")
	public Optional<MovieDTO> getMovieById(@PathVariable String id);
	
	
	@GetMapping("movies/getmoviebyname/{name}")
	public Optional<MovieDTO> getMovieByName(@PathVariable String name);
	

	@GetMapping("movies/getAllMovies")
	public List<MovieDTO> getAllMovies();
	
}
