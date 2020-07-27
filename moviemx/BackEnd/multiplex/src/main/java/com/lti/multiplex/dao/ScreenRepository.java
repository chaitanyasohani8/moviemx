
package com.lti.multiplex.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.lti.multiplex.document.Multiplex;
import com.lti.multiplex.document.Screen;

@Repository
public interface ScreenRepository extends MongoRepository<Screen, String> 
{
   public  Optional<Screen> findByScreenName(String name);
   
   public List<Screen> findByMovieId(String id);
   
   public List<Screen>findByMxId(String id);
   
   
}
