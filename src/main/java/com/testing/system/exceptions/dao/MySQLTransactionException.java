package com.testing.system.exceptions.dao;

public class MySQLTransactionException extends MySQLException {

	public MySQLTransactionException(String message){
		super(message);
	}
}
