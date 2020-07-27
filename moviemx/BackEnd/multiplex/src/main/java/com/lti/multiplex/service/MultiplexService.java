package com.lti.multiplex.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.lti.multiplex.document.Multiplex;

@Service
public interface MultiplexService
{
	public Multiplex addMultiplex(Multiplex multiplex );
	
	public Optional<Multiplex> updateMultiplex(Multiplex multiplex,String id );
	
	public void deleteMultiplex(String id );
	
	
	  public Optional<Multiplex> getMultiplexById(String id);
	  
	  public Optional<Multiplex> getMultiplexByName(String mxName);
		  
	  public List<Multiplex> getAllMultiplex();	 
	

}
