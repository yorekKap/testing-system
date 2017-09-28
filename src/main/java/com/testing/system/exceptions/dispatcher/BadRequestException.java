package com.testing.system.exceptions.dispatcher;

public class BadRequestException extends RuntimeException {

	public BadRequestException(String message){
		super(message);
	}
}
