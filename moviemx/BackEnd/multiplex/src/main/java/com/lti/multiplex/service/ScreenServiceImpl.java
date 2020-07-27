
package com.lti.multiplex.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.multiplex.dao.MultiplexRepository;
import com.lti.multiplex.dao.ScreenRepository;
import com.lti.multiplex.document.Multiplex;
import com.lti.multiplex.document.Screen;
import com.lti.multiplex.dto.AllotMovieToScreenDTO;
import com.lti.multiplex.dto.AllotScreenToMxDTO;
import com.lti.multiplex.dto.MovieDTO;
import com.lti.multiplex.dto.MovieDetailDTO;
import com.lti.multiplex.feignproxy.MicroMovieFeignProxy;

@Service
public class ScreenServiceImpl implements ScreenService {
    @Autowired
	MultiplexRepository multiplexRepository;
    
    @Autowired
   	MicroMovieFeignProxy proxy;
	ScreenRepository screenRepository;

	public ScreenServiceImpl(ScreenRepository screenRepository) {
		this.screenRepository = screenRepository;
	}

	@Override
	public Screen addScreenToMx(AllotScreenToMxDTO allotScreenToMxDTO) {
		Screen sdb = new Screen();
		sdb.setScreenName(allotScreenToMxDTO.getScreenName());
		sdb.setMxId(allotScreenToMxDTO.getMxId());
		Screen screenObj = screenRepository.save(sdb);
		return screenObj;
	}

	@Override
	public Optional<Screen> addMovieToScreen(AllotMovieToScreenDTO allotMovieToScreenDTO) {
		Optional<Screen> s1 = screenRepository.findById(allotMovieToScreenDTO.getScreenId()).map(sc -> {
			sc.setMovieId(allotMovieToScreenDTO.getMovieId());
			return screenRepository.save(sc);
		});

		return null;

	}
	
	@Override
	public void deleteScreen(String id)
	{
		screenRepository.deleteById(id);
	}


	@Override
	public Optional<Screen> getScreenById(String id) 
	{
		return screenRepository.findById(id);
	}

	@Override
	public Optional<Screen> getScreenByName(String name)
	{
		return screenRepository.findByScreenName(name);
	}
	@Override
	public List<Screen> getAllScreen() 
	{
		return screenRepository.findAll();
	}

	@Override
	public List<MovieDetailDTO> searchByMovieId(String id)
	{
		List<Screen> screenList= screenRepository.findByMovieId(id);
		
		List<MovieDetailDTO>movieDetailDTOList= new ArrayList<MovieDetailDTO>();
		
		MovieDetailDTO movieDetailDTO;
		
		for(Screen s:screenList)
		{
		    movieDetailDTO= new MovieDetailDTO();
			movieDetailDTO.setScreenId(s.getId());
			movieDetailDTO.setMxId(s.getMxId());
			movieDetailDTO.setMovieId(s.getMovieId());
			movieDetailDTO.setScreenName(s.getScreenName());
			
	        Optional <Multiplex> mxObj=	multiplexRepository.findById(s.getMxId());
			movieDetailDTO.setMultiplexName(mxObj.get().getMultiplexName());
			
			Optional<MovieDTO> movieDTO=proxy.getMovieById(s.getMovieId());
			movieDetailDTO.setMovieName(movieDTO.get().getName());
			
			movieDetailDTOList.add(movieDetailDTO);
		}
	
		return movieDetailDTOList;
	}
	
	@Override
	public List<MovieDetailDTO> searchByMovieName(String name)
	{
	  Optional<MovieDTO> movieDTO=proxy.getMovieByName(name);
		
	   return  searchByMovieId(movieDTO.get().getId());	
	}
	
	public List<MovieDetailDTO> searchByMultiplexId(String id)
	{
		List<Screen> screenListMX=screenRepository.findByMxId(id);
		
		List<MovieDetailDTO>movieDetailDTOListMX= new ArrayList<MovieDetailDTO>();
		
		MovieDetailDTO movieDetailDTOMX;
		
		for(Screen sMX:screenListMX)
		{
		    movieDetailDTOMX= new MovieDetailDTO();
			movieDetailDTOMX.setScreenId(sMX.getId());
			movieDetailDTOMX.setMxId(sMX.getMxId());
			movieDetailDTOMX.setMovieId(sMX.getMovieId());
			movieDetailDTOMX.setScreenName(sMX.getScreenName());
			
	        Optional <Multiplex> mxObjMX=	multiplexRepository.findById(sMX.getMxId());
			movieDetailDTOMX.setMultiplexName(mxObjMX.get().getMultiplexName());
			
			Optional<MovieDTO> movieDTOMX=proxy.getMovieById(sMX.getMovieId());
			movieDetailDTOMX.setMovieName(movieDTOMX.get().getName());
			
			movieDetailDTOListMX.add(movieDetailDTOMX);
		}
		
		return movieDetailDTOListMX;
	}

	@Override
	public List<MovieDetailDTO> searchByMultiplexName(String name) 
	{
		Optional<Multiplex> mxObj=multiplexRepository.findByMultiplexName(name);
		
		 return  searchByMultiplexId(mxObj.get().getId());	
		
	}
	
	
	

}
