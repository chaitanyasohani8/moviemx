package com.lti.multiplex.dao;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.lti.multiplex.document.Multiplex;

@Repository
public interface MultiplexRepository extends MongoRepository<Multiplex, String>
{
	public Optional<Multiplex> findByMultiplexName(String mxName); 
	

}
