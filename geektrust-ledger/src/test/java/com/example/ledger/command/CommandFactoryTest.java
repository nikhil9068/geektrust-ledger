package com.example.ledger.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

import com.example.ledger.balance.BalanceExecutor;
import com.example.ledger.enums.ValidCommands;
import com.example.ledger.exceptions.UnrecognizedCommandException;
import com.example.ledger.loan.LoanExecutor;
import com.example.ledger.payment.PaymentExecutor;

public class CommandFactoryTest {
	@Test
	public void getLoanInstancePositive() {
		CommandFactory commandFactory = new CommandFactory();
		String[] inputParams = {"LOAN", "IDIDI", "Dale", "5000", "1", "6"};
		Command command = null;
		
		try {
			command = commandFactory.getInstance(inputParams[0], inputParams).get();
		} catch (UnrecognizedCommandException e) {
			System.out.println("[EXCEPTION]: " + e.getMessage());
		}
		
		assertEquals(new LoanExecutor(inputParams).toString(), command.toString());
	}
	
	@Test
	public void getPaymentInstancePositive() {
		CommandFactory commandFactory = new CommandFactory();
		String[] inputParams = {"PAYMENT", "IDIDI", "Dale", "1000", "5"};
		Command command = null;
		
		try {
			command = commandFactory.getInstance(inputParams[0], inputParams).get();
		} catch (UnrecognizedCommandException e) {
			System.out.println("[EXCEPTION]: " + e.getMessage());
		}
		
		assertEquals(new PaymentExecutor(inputParams).toString(), command.toString());
	}
	
	@Test
	public void getBalanceInstancePositive() {
		CommandFactory commandFactory = new CommandFactory();
		String[] inputParams = {"BALANCE", "IDIDI", "Dale", "3"};
		Command command = null;
		
		try {
			command = commandFactory.getInstance(inputParams[0], inputParams).get();
		} catch (UnrecognizedCommandException e) {
			System.out.println("[EXCEPTION]: " + e.getMessage());
		}
		
		assertEquals(new BalanceExecutor(inputParams).toString(), command.toString());
	}
	
	@Test
	public void getUnrecognizedCommand() {
		CommandFactory commandFactory = new CommandFactory();
		String[] inputParams = {"CMD", "IDIDI", "Dale", "3"};
		UnrecognizedCommandException e = null;
		
		try {
			commandFactory.getInstance(inputParams[0], inputParams).get();
		} catch (UnrecognizedCommandException ex) {
			e = ex;
		}
		
		assertEquals(new UnrecognizedCommandException("Illegal Command, valid commands are: " + Arrays.toString(ValidCommands.values()), inputParams[0]).getMessage(), e.getMessage());
	}
}