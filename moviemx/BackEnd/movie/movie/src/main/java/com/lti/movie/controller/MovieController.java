package com.lti.movie.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.lti.movie.document.Movie;
import com.lti.movie.dto.MessageDTO;
import com.lti.movie.dto.UserExceptionDto;
import com.lti.movie.exception.BoundaryException;
import com.lti.movie.exception.DuplicateRecordException;
import com.lti.movie.exception.ResourceNotFoundException;
import com.lti.movie.service.MovieService;
import com.lti.movie.validation.DataValidator;


@RestController
@RequestMapping("/movies")
@Validated
public class MovieController
{
	
	MovieService movieService;
	
	MovieController(MovieService movieService)
	{
		this.movieService=movieService;
	}
	
	@PostMapping("/addmovie")
	public ResponseEntity<Movie>addMovie( @Valid @RequestBody Movie movie)
	{
	  Optional<Movie> movieDbObj=movieService.getMovieByName(movie.getName());
	  
	  
	  if(movieDbObj.isPresent()&&movieDbObj.get().getName().equals(movie.getName()))
	  {
		  throw new DuplicateRecordException("movie name already exist");
	  }
	  else {
		Movie movieObj=movieService.addMovie(movie);
		
		ResponseEntity<Movie> response= new ResponseEntity<Movie>(movieObj,HttpStatus.OK);
		return response;	
	  }
	  
	}
	
	@PutMapping("/updatemovie/{id}")
	public ResponseEntity<Movie> updateMovie(@ Valid @RequestBody Movie movie,@PathVariable @NotBlank  String id)
	{
		   Optional<Movie> movieDbObj=movieService.getMovieById(id);
		   if(movieDbObj.isPresent())
		   {
			   Optional<Movie> movieDbObj1= movieService.getMovieByName(movie.getName());
			   if(movieDbObj1.isPresent())
			   {
				  throw new  DuplicateRecordException("movie name already exist");
			   }  
			   else 
			   {
				   Optional<Movie> movieOb = movieService.updateMovie(movie, id);
					if(movieOb.isPresent())
					{
						ResponseEntity<Movie> response= new ResponseEntity<Movie>(HttpStatus.OK);
						return response;
					}
					
					else 
					{
						 throw new ResourceNotFoundException("movie can not be update");
					}
				   
			   }
			
		   }
		   else
			{
				throw new ResourceNotFoundException("movie id not found");
			}
		   

	}
	
	@DeleteMapping("/deletemoviebyid/{id}")
	public ResponseEntity<MessageDTO> deleteMovieById(@PathVariable @NotBlank String id)
	{
		Optional<Movie> movieOb= movieService.getMovieById(id);
		if(movieOb.isPresent())
		{
		   movieService.deleteMovie(id);
		   
		   MessageDTO msg= new MessageDTO("id deleted succesfully");   
		   ResponseEntity<MessageDTO> response= new ResponseEntity<MessageDTO>(msg,HttpStatus.OK);
		   return response;
		}
		else 
		{
          throw new ResourceNotFoundException("movie id not found");
          //ResponseEntity<Movie> mvtest= new ResponseEntity<Movie>()
		}
	
	}
	
	@GetMapping("/getmoviebyid/{id}")
	public ResponseEntity<Optional<Movie>> getMovieById(@PathVariable @NotBlank  String id)
	{
	
	   Optional<Movie> movieOb= movieService.getMovieById(id);
	   if(movieOb.isPresent())
	   {
		   ResponseEntity<Optional<Movie>> response= new ResponseEntity<Optional<Movie>>(movieOb,HttpStatus.OK);
		   return response;		 
	   }
	   else 
	   {
		   throw new  ResourceNotFoundException("movie Not Found on this id:"+id); 
	   }
	}
	
	@GetMapping("/getmoviebyname/{name}")
	public ResponseEntity<Optional<Movie>> getMovieByName(@PathVariable  @NotBlank String name)
	{
		Optional<Movie> movieOb=  movieService.getMovieByName(name);
		   if(movieOb.isPresent())
		   {
			   ResponseEntity<Optional<Movie>> response= new ResponseEntity<Optional<Movie>>(movieOb,HttpStatus.OK);
			   return response;		 
		   }
		   else 
		   {
			   throw new  ResourceNotFoundException("movie Not Found for this name:"+name); 
		   }
	   
	}

	@GetMapping("/getallmovies")
	public ResponseEntity<List<Movie>> getAllMovies()
	{
	   List<Movie> movieList= movieService.getAllMovies();
	   if(movieList.size()>0)
	   {
		   ResponseEntity<List<Movie>> response = new ResponseEntity<List<Movie>>(movieList,HttpStatus.OK);
		   return response;
	   }
	   else 
	   {
		   throw new  ResourceNotFoundException("No movie Found");
	   }
	}

	

}
