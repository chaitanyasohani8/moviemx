
package com.lti.multiplex.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.lti.multiplex.document.Multiplex;
import com.lti.multiplex.document.Screen;
import com.lti.multiplex.dto.AllotMovieToScreenDTO;
import com.lti.multiplex.dto.AllotScreenToMxDTO;
import com.lti.multiplex.dto.MovieDetailDTO;

@Service
public interface ScreenService 
{
	public Screen addScreenToMx(AllotScreenToMxDTO allotScreenToMxDTO);
	
    
	public Optional<Screen> addMovieToScreen(AllotMovieToScreenDTO allotMovieToScreenDTO);
	
	public void deleteScreen(String id );
	
	 public Optional<Screen> getScreenById(String id);
	  
	  public Optional<Screen> getScreenByName(String mxName);
		  
	  public List<Screen> getAllScreen();	
	  
	  public List<MovieDetailDTO>searchByMovieId(String id);
	  
	  public List<MovieDetailDTO> searchByMovieName(String name);
	  
	  public List<MovieDetailDTO> searchByMultiplexId(String id);

	  public List<MovieDetailDTO> searchByMultiplexName(String id);
}
