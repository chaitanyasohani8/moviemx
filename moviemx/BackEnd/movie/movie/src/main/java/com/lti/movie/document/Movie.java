package com.lti.movie.document;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Document("moviedb")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Movie 
{
	@Id
	private String	id;
	@javax.validation.constraints.NotBlank
	private String name;
	@javax.validation.constraints.NotBlank
	private String	category;
	@javax.validation.constraints.NotBlank
	private String	producer;
	@javax.validation.constraints.NotBlank
	private String	director;
	
	
//(f)private Date	ReleaseDate 
	
	

    
}
