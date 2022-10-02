package com.example.ledger.exceptions;

public class UnrecognizedCommandException extends Exception {
	private static final long serialVersionUID = 1L;
	String commandName;
	String message;
	
	public UnrecognizedCommandException(String message, String commandName) {
		super(message);
		this.commandName = commandName;
		this.message = message;
	}
	
	@Override
    public String getMessage() {
        return "Command '" + commandName + "' is unrecognized by Ledger System. " + message;
    }
}
