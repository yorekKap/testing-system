package com.testing.system.exceptions.dao;

public class MySQLBadQueryException extends MySQLException{
		
	public MySQLBadQueryException(String message) {
		super(message);
	}
}
