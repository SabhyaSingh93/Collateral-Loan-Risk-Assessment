package com.cts.loan.exception;

/**
 * 
 * Exception class
 * this exception is thrown when there is an invalid
 * authorization
 *
 */
public class InvalidAuthorizationException extends Exception {

	private static final long serialVersionUID = 1L;

	public InvalidAuthorizationException(String message) {
		super(message);
	}

}
