package com.example.ledger.exceptions;

public class InvalidNumberOfArgumentsException extends Exception {
	private static final long serialVersionUID = 1L;
	String commandName;
	String message;
	
	public InvalidNumberOfArgumentsException(String message) {
		super(message);
	}
	
	@Override
    public String getMessage() {
        return "Invalid Number of Arguments Passed !!! Please enter valid number of arguments.";
    }
}