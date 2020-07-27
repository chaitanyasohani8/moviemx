package com.lti.movie.dto;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class ApiError 
{
	 private HttpStatus status; 
	    private List<String> errors;
	    private String message;
	 
	    public ApiError(HttpStatus status, List<String> errors,String message) 
	    {
	        super();
	        this.status = status;
	        this.message = message;
	        this.errors = errors;
	    }
	 
	    public ApiError(HttpStatus status,  String error,String message) {
	        super();
	        this.status = status;
	        this.message = message;
	        errors = Arrays.asList(error);
	    }

}
