
package com.lti.multiplex.controller;

import java.io.Console;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lti.multiplex.document.Multiplex;
import com.lti.multiplex.document.Screen;
import com.lti.multiplex.dto.AllotMovieToScreenDTO;
import com.lti.multiplex.dto.AllotScreenToMxDTO;
import com.lti.multiplex.dto.MovieDetailDTO;
import com.lti.multiplex.service.MultiplexService;
import com.lti.multiplex.service.ScreenService;

@RestController

@RequestMapping("/screen")
public class ScreenController {

	ScreenService screenService;

	public ScreenController(ScreenService screenService) {
		this.screenService = screenService;
	}

	@PostMapping("/allotscreentomx")
	public ResponseEntity<Screen> allotScreenToMultiplex(@RequestBody AllotScreenToMxDTO allotScreenToMxDTO) {
		Screen screenOb = screenService.addScreenToMx(allotScreenToMxDTO);

		if (!screenOb.equals(null)) {
			ResponseEntity<Screen> response = new ResponseEntity<Screen>(screenOb, HttpStatus.OK);
			return response;
		}
		return null;

	}

	@PostMapping("/allotmovietoscreen")
	public ResponseEntity<Screen> allotMovieToScreen(@RequestBody AllotMovieToScreenDTO allotMovieToScreenDTO) 
	{
      Optional<Screen> sc= screenService.getScreenById(allotMovieToScreenDTO.getScreenId());
      System.out.println(sc);
		if(sc.isPresent())
		{
			try {
			if(sc.get().getMovieId().equals(null))
			{
				screenService.addMovieToScreen(allotMovieToScreenDTO);
				ResponseEntity<Screen> response = new ResponseEntity<Screen>( HttpStatus.OK);	
				return response;
			}
			else 
			{  //movie already there
				ResponseEntity<Screen> response = new ResponseEntity<Screen>( HttpStatus.CONFLICT);	
				return response;
			}}
			
		catch(NullPointerException ex)
			{
			screenService.addMovieToScreen(allotMovieToScreenDTO);
			ResponseEntity<Screen> response2 = new ResponseEntity<Screen>( HttpStatus.OK);	
			return response2;
			}
		}
        
		else 
		{
			 ResponseEntity<Screen> sc1= new ResponseEntity<Screen>(HttpStatus.BAD_REQUEST);
			 return sc1;
		}
		
		
      
	}

	/*
	 * @PostMapping("/allotmovietoscreen") public ResponseEntity<Optional<Screen>>
	 * allotMovieToScreen(@RequestBody AllotMovieToScreenDTO allotMovieToScreenDTO)
	 * {
	 * 
	 * System.out.println(allotMovieToScreenDTO.toString()); Optional<Screen>
	 * screenOb = screenService.addMovieToScreen(allotMovieToScreenDTO);
	 * 
	 * ResponseEntity<Optional<Screen>> response = new
	 * ResponseEntity<Optional<Screen>>(screenOb, HttpStatus.OK); return response;
	 * 
	 * 
	 * if (!screenOb.equals(null)) { ResponseEntity<Optional<Screen>> response = new
	 * ResponseEntity<Optional<Screen>>(screenOb, HttpStatus.OK); return response; }
	 * return null;
	 * 
	 * }
	 */
	@DeleteMapping("/deletescreenbyid/{id}")
	public ResponseEntity deleteScreenById(@PathVariable String id) {
		screenService.deleteScreen(id);
		ResponseEntity<Multiplex> response = new ResponseEntity<Multiplex>(HttpStatus.OK);
		return response;

	}

	@GetMapping("/getscreenbyid/{id}")
	public ResponseEntity<Screen> getScreenById(@PathVariable String id) {
		Optional<Screen> screenObj = screenService.getScreenById(id);

		ResponseEntity<Screen> response = new ResponseEntity(screenObj, HttpStatus.OK);
		//response.getBody();

		return response;

	}

	@GetMapping("/getscreenbyname/{name}")
	public ResponseEntity<Screen> getScreenByName(@PathVariable String name) {
		Optional<Screen> screenObj = screenService.getScreenByName(name);

		ResponseEntity<Screen> response = new ResponseEntity(screenObj, HttpStatus.OK);
		return response;

	}

	@GetMapping("/getallscreen")
	public ResponseEntity<List<Screen>> getAllScreen() {
		List<Screen> screenObjList = screenService.getAllScreen();

		ResponseEntity<List<Screen>> response = new ResponseEntity(screenObjList, HttpStatus.OK);
		return response;

	}
	
	@GetMapping("/searchbymovieid/{id}")
	public List<MovieDetailDTO> searchByMovieId(@PathVariable String id)
	{
	   return screenService.searchByMovieId(id);
	 
	}
	
	@GetMapping("/searchbymoviename/{name}")
	public List<MovieDetailDTO> searchByMovieName(@PathVariable String name)
	{
		return screenService.searchByMovieName(name); 
	}
	
	@GetMapping("/searchbymultiplexid/{id}")
	public List<MovieDetailDTO> searchByMultiplexId(@PathVariable String id)
	{
		return screenService.searchByMultiplexId(id); 
	}
	
	@GetMapping("/searchbymultiplexname/{name}")
	public List<MovieDetailDTO> searchByMultiplexName(@PathVariable String name)
	{
		return screenService.searchByMultiplexName(name); 
	}


}
