package com.cts.loan.exception;

public class DataNotPresentException extends Exception {
	/**
	 * In this function used to return {@link DataNotPresentException} when any data is missing from repository
	 *  
	 *This class extends the Exception class and send a message based on Exception  
	 */
	private static final long serialVersionUID = 1L;
	public DataNotPresentException(String message) {
		super(message);
		
	}
	

}
