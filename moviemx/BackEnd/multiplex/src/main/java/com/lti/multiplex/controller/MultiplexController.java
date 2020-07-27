package com.lti.multiplex.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.BindingResult;

import com.lti.multiplex.document.Multiplex;
import com.lti.multiplex.dto.MovieDTO;
import com.lti.multiplex.dto.MovieDetailDTO;
import com.lti.multiplex.feignproxy.MicroMovieFeignProxy;
import com.lti.multiplex.service.MultiplexService;

@RestController
@RequestMapping("/multiplex")
public class MultiplexController {
  
	@Autowired
	MicroMovieFeignProxy proxy;
	
	MultiplexService multiplexService;

	
	public MultiplexController(MultiplexService multiplexService)
	{
		this.multiplexService = multiplexService;
	}

	@PostMapping("/addmultiplex")
	public ResponseEntity<Multiplex> addMultiplex(@RequestBody Multiplex multiplex) {
		Multiplex multiplexObj = multiplexService.addMultiplex(multiplex);

		if (!multiplexObj.equals(null)) {
			ResponseEntity<Multiplex> response = new ResponseEntity<Multiplex>(multiplexObj, HttpStatus.OK);
			return response;
		}
		return null;

	}

	
	@PutMapping("/updatemultiplex/{id}")
	public ResponseEntity<Multiplex> updateMultiplex(@RequestBody Multiplex multiplex, @PathVariable String id)
			throws Exception 
	{

		Optional<Multiplex> multiplexOb = multiplexService.updateMultiplex(multiplex, id);
		if (multiplexOb.isPresent()) {
			ResponseEntity<Multiplex> response = new ResponseEntity<Multiplex>(HttpStatus.OK);
			return response;
		} else {
			throw new Exception();
		}

	}
	  
		
	@DeleteMapping("/deletemultiplex/{id}")
	public ResponseEntity<Multiplex> deleteMultiplex(@PathVariable String id) throws Exception 
	{
		if (!id.equals(null)) 
		{

			multiplexService.deleteMultiplex(id);
			ResponseEntity<Multiplex> response = new ResponseEntity<Multiplex>(HttpStatus.OK);
			return response;

		}

		else {
			throw new Exception();
		}

	}
	
	
	@GetMapping("/getmultiplexbyid/{id}")
	public ResponseEntity<Multiplex> getMultiplexById(@PathVariable String id) 
	{
		Optional <Multiplex> multiplexObj=multiplexService.getMultiplexById(id);
		
		ResponseEntity<Multiplex> response= new ResponseEntity(multiplexObj,HttpStatus.OK);
		return response;

	}
	 
	  
		
	@GetMapping("/getmultiplexbyname/{mxName}")
	public ResponseEntity<Multiplex> getMultiplexByName(@PathVariable String mxName)
	{
	   Optional<Multiplex> multiplexObj=  multiplexService.getMultiplexByName(mxName);
	   
	   ResponseEntity<Multiplex> response= new ResponseEntity(multiplexObj,HttpStatus.OK);
		return response;

	}
		  
	@GetMapping("/getallmultiplex")
	public ResponseEntity<List<Multiplex>> getAllMultiplex()
	{
	  List<Multiplex>multiplexObjList= multiplexService.getAllMultiplex();	
	  
	  ResponseEntity<List<Multiplex>> response= new ResponseEntity(multiplexObjList,HttpStatus.OK);
	  return response; 
	}
	
	@GetMapping("/getmovietest/{id}")
	public Optional<MovieDTO> getTest(@PathVariable String id)
	{
		System.out.println("hello");
		return proxy.getMovieById(id);
	 
	}
		
	

		 
	  
	 
}
