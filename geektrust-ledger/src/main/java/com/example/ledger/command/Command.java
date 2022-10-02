package com.example.ledger.command;

import com.example.ledger.exceptions.InvalidNumberOfArgumentsException;

public interface Command {
	void execute() throws IllegalArgumentException, InvalidNumberOfArgumentsException;
}
