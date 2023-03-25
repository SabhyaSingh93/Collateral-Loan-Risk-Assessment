package com.cts.auth.model;

public enum Messages {
	LOGIN("Login"),
	INVALIDTOKEN("Invalid Token");
	private String message;
	public String getMessage() {
		return message;
	}
	private Messages(String message) {
		this.message = message;
	}
}
