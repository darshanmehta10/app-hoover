package com.test.hoover.exception;

/**
 * 
 * @author Darshan Mehta
 * Custom Exception class to throw errors
 */
public class InvalidRequestException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public InvalidRequestException(String message){
		super(message);
	}

}
