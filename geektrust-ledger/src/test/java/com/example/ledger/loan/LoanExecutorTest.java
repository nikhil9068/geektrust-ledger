package com.example.ledger.loan;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.example.ledger.exceptions.InvalidNumberOfArgumentsException;
import com.example.ledger.exceptions.UnrecognizedCommandException;

public class LoanExecutorTest {
	@Test
	public void executePositiveTest() throws IllegalArgumentException, RuntimeException, UnrecognizedCommandException, InvalidNumberOfArgumentsException {
		String[] inputParams = {"LOAN", "IDIDI", "Dale", "5000", "1", "6"};
		
		LoanExecutor loanExecutor = new LoanExecutor(inputParams);
		
		loanExecutor.execute();
	}
	
	@Test
	public void executeInvalidNumberOfArgumentsTest() throws IllegalArgumentException, RuntimeException, UnrecognizedCommandException, InvalidNumberOfArgumentsException {
		String[] inputParams = {"LOAN", "IDIDI", "Dale", "5000", "1"};
		InvalidNumberOfArgumentsException ex = null;
		
		LoanExecutor loanExecutor = new LoanExecutor(inputParams);
		
		try {
			loanExecutor.execute();
		} catch (InvalidNumberOfArgumentsException e) {
			ex = e;
		}
		
		assertEquals(new InvalidNumberOfArgumentsException("Number of parameters passed: " + 
        												inputParams.length).getMessage(), ex.getMessage());
	}
	
	@Test
	public void executeInvalidArgumentFormatTest() throws IllegalArgumentException, RuntimeException, UnrecognizedCommandException, InvalidNumberOfArgumentsException {
		String[] inputParams = {"LOAN", "IDIDI", "Dale", "5000", "abc", "6"};
		IllegalArgumentException ex = null;
		
		LoanExecutor loanExecutor = new LoanExecutor(inputParams);
		
		try {
			loanExecutor.execute();
		} catch (IllegalArgumentException e) {
			ex = e;
		}
		
		assertEquals(new IllegalArgumentException("For input string: \"" + 
        												inputParams[4] + "\"").getMessage(), ex.getMessage());
	}
}