package com.lti.multiplex.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.lti.multiplex.dao.MultiplexRepository;
import com.lti.multiplex.document.Multiplex;

@Service
public class MultiplexServiceImpl implements MultiplexService {
	MultiplexRepository multiplexRepository;

	MultiplexServiceImpl(MultiplexRepository multiplexRepository) {
		this.multiplexRepository = multiplexRepository;
	}

	@Override
	public Multiplex addMultiplex(Multiplex multiplex) 
	{
		Multiplex MultiplexObj=multiplexRepository.save(multiplex);
		
		return MultiplexObj;
		
	}


  @Override 
  public Optional<Multiplex> updateMultiplex(Multiplex multiplex,String id) {
  
  
  return Optional.of(multiplexRepository.findById(id).map(mv-> 
  {
    mv.setMultiplexName(multiplex.getMultiplexName());
    mv.setAddress(multiplex.getAddress());
    mv.setNumberOfScreens(multiplex.getNumberOfScreens());
    return multiplexRepository.save(mv);
  }) 
     .orElseGet(()-> 
     { multiplex.setId(id); 
      return multiplexRepository.save(multiplex); }));
  
  }
  
	@Override
	public void deleteMultiplex(String id) 
	{

		multiplexRepository.deleteById(id);

	}

	
	@Override
	public Optional<Multiplex> getMultiplexById(String id)
	{
		return multiplexRepository.findById(id);

	}
	  
		
	@Override
	public Optional<Multiplex> getMultiplexByName(String mxName) {
		return multiplexRepository.findByMultiplexName(mxName);

	}
		  
			
	@Override
	public List<Multiplex> getAllMultiplex() 
	{
		return multiplexRepository.findAll();

	}
		 
 
  }
 