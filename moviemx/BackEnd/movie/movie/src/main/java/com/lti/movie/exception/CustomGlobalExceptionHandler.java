package com.lti.movie.exception;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.lti.movie.dto.ApiError;
import com.lti.movie.dto.UserExceptionDto;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler
{

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> errors = new ArrayList<String>();
		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
			errors.add(error.getField() + ": " + error.getDefaultMessage());
		}
		for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
			errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
		}

		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, errors, ex.getLocalizedMessage());
		return handleExceptionInternal(ex, apiError, headers, apiError.getStatus(), request);
	} 

	@ExceptionHandler(BoundaryException.class)
	public ResponseEntity<UserExceptionDto> boundaryExceptionHanler(BoundaryException ex) {
		UserExceptionDto userExceptionDto = 
				new UserExceptionDto(ex.getMessage(), 
									HttpStatus.BAD_REQUEST.value(), 
									System.currentTimeMillis());
		ResponseEntity<UserExceptionDto> response = 
				new ResponseEntity<UserExceptionDto>(userExceptionDto, HttpStatus.BAD_REQUEST);
		return response;
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<UserExceptionDto> boundaryExceptionHanler(ResourceNotFoundException ex) 
	{
		UserExceptionDto userExceptionDto = 
				new UserExceptionDto(ex.getMessage(), 
									HttpStatus.BAD_REQUEST.value(), 
									System.currentTimeMillis());
		ResponseEntity<UserExceptionDto> response = 
				new ResponseEntity<UserExceptionDto>(userExceptionDto, HttpStatus.BAD_REQUEST);
		return response;
		
	}    
	
	@ExceptionHandler(DuplicateRecordException.class)
	public ResponseEntity<UserExceptionDto> DuplicateRecordExceptionHanler(DuplicateRecordException ex) 
	{
		UserExceptionDto userExceptionDto = 
				new UserExceptionDto(ex.getMessage(), 
									HttpStatus.BAD_REQUEST.value(), 
									System.currentTimeMillis());
		ResponseEntity<UserExceptionDto> response = 
				new ResponseEntity<UserExceptionDto>(userExceptionDto, HttpStatus.BAD_REQUEST);
		return response;
		
	}    
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<UserExceptionDto> ConstraintViolationExceptionHandler(ConstraintViolationException ex)
    {
		System.out.println(ex.getMessage());
		UserExceptionDto userExceptionDto = 
				new UserExceptionDto(ex.getMessage(), 
									HttpStatus.BAD_REQUEST.value(), 
									System.currentTimeMillis());    
		ResponseEntity<UserExceptionDto> response = 
				new ResponseEntity<UserExceptionDto>(userExceptionDto, HttpStatus.BAD_REQUEST);
		return response;
    	
    }
	
	/*@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<UserExceptionDto> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {
		System.out.println(ex.getMessage());
		UserExceptionDto userExceptionDto = new UserExceptionDto(ex.getMessage(), HttpStatus.BAD_REQUEST.value(),
				System.currentTimeMillis());
		ResponseEntity<UserExceptionDto> response = new ResponseEntity<UserExceptionDto>(userExceptionDto,
				HttpStatus.BAD_REQUEST);
		return response;
	}*/

}
