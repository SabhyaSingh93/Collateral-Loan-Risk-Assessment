package com.cts.clra.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cts.clra.model.ErrorDetails;

/**
 * 
 *This class is annotated with @ControllerAdvice which means that this class 
 *will be able to handle the exceptions 
 *occured from any of the Controllers
 */
@ControllerAdvice
public class GlobalExceptionHandler extends Exception {

	private static final long serialVersionUID = 1L;
	
	/**
	 * The @ExceptionHandler annotation is required to handle the type of exceptions which are 
	 * thrown and the corresponding method will be able to 
	 * handle the exceptions.
	 * @param exception
	 * @return
	 */
	@ExceptionHandler(InvalidAuthorizationException.class)
	public ResponseEntity<ErrorDetails> invalidAuthorizationHandler(Exception exception) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage());
		return new ResponseEntity<>(errorDetails, HttpStatus.FORBIDDEN);
	}

}
