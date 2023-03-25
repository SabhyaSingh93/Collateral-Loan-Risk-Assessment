package com.cts.clra.model;

public enum Messages {
	LOGIN("Login"),
	AUTHSUCCESS("Authorization Successful"),
	AUTHUNSUCCESS("Authorization Unsuccessful"),
	INVALIDTOKEN("Invalid Token"),
	NODATA("This data is not available in database"),
	NOIDFOUND("The Collateral with  given id is not found");
	private String message;
	public String getMessage() {
		return message;
	}
	private Messages(String message) {
		this.message = message;
	}
}
