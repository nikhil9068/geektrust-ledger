package com.example.ledger.balance;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.example.ledger.exceptions.InvalidNumberOfArgumentsException;
import com.example.ledger.loan.LoanExecutor;

public class BalanceExecutorTest {
	@Test
	public void executeBalancePositiveTest() throws IllegalArgumentException, InvalidNumberOfArgumentsException {
		String[] inputParams = {"BALANCE", "IDIDI", "Dale", "3"};
		
		String[] inputParams1 = {"LOAN", "IDIDI", "Dale", "5000", "1", "6"};
		LoanExecutor loanExecutor = new LoanExecutor(inputParams1);
		loanExecutor.execute();
		
		BalanceExecutor balanceExecutor = new BalanceExecutor(inputParams);
		
		balanceExecutor.execute();
	}
	
	@Test
	public void executeBalanceInvalidNumberOfArgumentsTest() throws IllegalArgumentException, InvalidNumberOfArgumentsException {
		String[] inputParams = {"BALANCE", "IDIDI", "Dale", "3", "abc"};
		
		String[] inputParams1 = {"LOAN", "IDIDI", "Dale", "5000", "1", "6"};
		LoanExecutor loanExecutor = new LoanExecutor(inputParams1);
		loanExecutor.execute();
		
		BalanceExecutor balanceExecutor = new BalanceExecutor(inputParams);
		InvalidNumberOfArgumentsException ex = null;
		
		try {
			balanceExecutor.execute();
		} catch (InvalidNumberOfArgumentsException e) {
			ex = e;
		}
		
		assertEquals(new InvalidNumberOfArgumentsException("Number of parameters passed: " + 
				inputParams.length).getMessage(), ex.getMessage());
	}
	
	@Test
	public void executeBalanceInvalidArgumentFormatTest() throws IllegalArgumentException, InvalidNumberOfArgumentsException {
		String[] inputParams = {"BALANCE", "IDIDI", "Dale", "abc"};
		
		String[] inputParams1 = {"LOAN", "IDIDI", "Dale", "5000", "1", "6"};
		LoanExecutor loanExecutor = new LoanExecutor(inputParams1);
		loanExecutor.execute();
		
		BalanceExecutor balanceExecutor = new BalanceExecutor(inputParams);
		IllegalArgumentException ex = null;
		
		try {
			balanceExecutor.execute();
		} catch (IllegalArgumentException e) {
			ex = e;
		}
		
		assertEquals(new IllegalArgumentException("For input string: \"" + 
				inputParams[3] + "\"").getMessage(), ex.getMessage());
	}
}