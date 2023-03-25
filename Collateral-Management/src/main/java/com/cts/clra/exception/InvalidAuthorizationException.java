package com.cts.clra.exception;


public class InvalidAuthorizationException extends Exception {

	/**
	 * In this function used to return {@link InvalidAuthorizationException} in the palce of validation
	 * This Message is shows the Your entered is credentials is wrong and check your entered credentials.
	 *  
	 *This class extends the Exception class and send a message based on Exception  
	 */
	private static final long serialVersionUID = 1L;
	public InvalidAuthorizationException(String message) {
		super(message);
		
	}
	
	

}
