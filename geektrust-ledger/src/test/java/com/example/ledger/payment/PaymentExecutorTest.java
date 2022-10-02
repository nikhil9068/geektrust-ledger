package com.example.ledger.payment;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.example.ledger.exceptions.InvalidNumberOfArgumentsException;
import com.example.ledger.loan.LoanExecutor;

public class PaymentExecutorTest {
	@Test
	public void executePositiveTest() throws IllegalArgumentException, InvalidNumberOfArgumentsException {
		String[] inputParams = {"PAYMENT", "IDIDI", "Dale", "1000", "5"};
		
		String[] inputParams1 = {"LOAN", "IDIDI", "Dale", "5000", "1", "6"};
		LoanExecutor loanExecutor = new LoanExecutor(inputParams1);
		loanExecutor.execute();
		
		PaymentExecutor paymentExecutor = new PaymentExecutor(inputParams);
		
		paymentExecutor.execute();
	}
	
	@Test
	public void executeInvalidNumberOfArgumentsTest() throws IllegalArgumentException, InvalidNumberOfArgumentsException {
		String[] inputParams = {"PAYMENT", "IDIDI", "Dale", "1000", "5", "6"};
		
		String[] inputParams1 = {"LOAN", "IDIDI", "Dale", "5000", "1", "6"};
		LoanExecutor loanExecutor = new LoanExecutor(inputParams1);
		loanExecutor.execute();
		
		PaymentExecutor paymentExecutor = new PaymentExecutor(inputParams);
		InvalidNumberOfArgumentsException ex = null;
		
		try {
			paymentExecutor.execute();
		} catch (InvalidNumberOfArgumentsException e) {
			ex = e;
		}
		
		assertEquals(new InvalidNumberOfArgumentsException("Number of parameters passed: " + 
				inputParams.length).getMessage(), ex.getMessage());
	}
	
	@Test
	public void executeInvalidArgumentFormatTest() throws IllegalArgumentException, InvalidNumberOfArgumentsException {
		String[] inputParams = {"PAYMENT", "IDIDI", "Dale", "1000", "abc"};
		
		String[] inputParams1 = {"LOAN", "IDIDI", "Dale", "5000", "1", "6"};
		LoanExecutor loanExecutor = new LoanExecutor(inputParams1);
		loanExecutor.execute();
		
		PaymentExecutor paymentExecutor = new PaymentExecutor(inputParams);
		IllegalArgumentException ex = null;
		
		try {
			paymentExecutor.execute();
		} catch (IllegalArgumentException e) {
			ex = e;
		}
		
		assertEquals(new IllegalArgumentException("For input string: \"" + 
				inputParams[4] + "\"").getMessage(), ex.getMessage());
	}
}