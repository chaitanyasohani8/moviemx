package com.lti.multiplex.document;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Document("screendb")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Screen 
{
	@Id
	private String	id;
	private String  screenName;
	private String	mxId;
	private String	movieId;
	
	
//(f)private Date	ReleaseDate 
    
}
