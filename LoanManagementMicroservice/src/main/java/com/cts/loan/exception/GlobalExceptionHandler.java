package com.cts.loan.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.cts.loan.model.ErrorDetails;

/**
 * 
 * @ControllerAdvice to write global code that can be applied 
 * to a wide range of controllers
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler extends Exception {

	private static final long serialVersionUID = 1L;
	
	@ExceptionHandler(InvalidAuthorizationException.class)
	public ResponseEntity<ErrorDetails> invalidAuthorizationHandler(Exception exception) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage());
		return new ResponseEntity<>(errorDetails, HttpStatus.FORBIDDEN);
	}

}
