package com.lti.movie.dto;

import lombok.Data;

@Data
public class MessageDTO 
{
  String message;

/**
 * @param message
 */
public MessageDTO(String message) {
	super();
	this.message = message;
}
  
  
}
