package com.lti.multiplex.dto;

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
public class MovieDetailDTO 
{
	private String screenId;
	private String mxId;
	private String movieId;
	private String screenName;
	private String multiplexName;
	private String movieName;
	
}
