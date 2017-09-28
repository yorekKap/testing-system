package com.testing.system.exceptions.service;

public class AuthenticationException extends Exception {

	public static String WRONG_USERNAME = "wrong-username";
	public static String WRONG_PASSWORD = "wrong-password";

	private String reason;

	public AuthenticationException(String reason) {
		this.reason = reason;
	}

	public String getReason() {
		return reason;
	}

}
