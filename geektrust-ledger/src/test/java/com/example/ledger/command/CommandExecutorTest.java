package com.example.ledger.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

import com.example.ledger.exceptions.InvalidNumberOfArgumentsException;
import com.example.ledger.exceptions.UnrecognizedCommandException;

public class CommandExecutorTest {
	@Test
	public void executeCommandLoanPositiveTest() throws NullPointerException, IllegalArgumentException, RuntimeException, UnrecognizedCommandException, InvalidNumberOfArgumentsException {
		CommandExecutor commandExecutor = new CommandExecutor();
		
		commandExecutor.executeCommand("LOAN IDIDI Dale 10000 5 4");
	}
	
	@Test
	public void executeCommandPaymentPositiveTest() throws NullPointerException, IllegalArgumentException, RuntimeException, UnrecognizedCommandException, InvalidNumberOfArgumentsException {
		CommandExecutor commandExecutor = new CommandExecutor();
		
		commandExecutor.executeCommand("LOAN IDIDI Dale 10000 5 4");
		commandExecutor.executeCommand("PAYMENT IDIDI Dale 1000 5");
	}
	
	@Test
	public void executeCommandBalancePositiveTest() throws NullPointerException, IllegalArgumentException, RuntimeException, UnrecognizedCommandException, InvalidNumberOfArgumentsException {
		CommandExecutor commandExecutor = new CommandExecutor();
		
		commandExecutor.executeCommand("LOAN IDIDI Dale 10000 5 4");
		commandExecutor.executeCommand("BALANCE IDIDI Dale 3");
		commandExecutor.executeCommand("BALANCE IDIDI Dale 6");
	}
	
	@Test
	public void executeCommandLoanNegativeEmptyLineTest() throws IllegalArgumentException, RuntimeException, UnrecognizedCommandException, InvalidNumberOfArgumentsException {
		CommandExecutor commandExecutor = new CommandExecutor();
		NullPointerException ex = null;
		
		try {
			commandExecutor.executeCommand("");
		} catch(NullPointerException e) {
			ex = e;
		}
		
		Assertions.assertEquals(new NullPointerException("Empty Line in File").getMessage(), ex.getMessage());
	}
	
	@Test
	public void executeCommandLoanNegativeTabInLineTest() {
		CommandExecutor commandExecutor = new CommandExecutor();
		RuntimeException ex = null;
		
		try {
			commandExecutor.executeCommand("LOAN	IDIDI Dale 10000 5 4");
		} catch(RuntimeException e) {
			ex = e;
		}
		
		Assertions.assertEquals(new RuntimeException("No tab should be present in the command line").getMessage(), ex.getMessage());
	}
	
	@Test
	public void executeCommandInvalidCommandTest() {
		CommandExecutor commandExecutor = new CommandExecutor();
		RuntimeException ex = null;
		
		commandExecutor.executeCommand("CMD IDIDI Dale 10000 5 4");
	}
	
	@Test
	public void executeCommandInvalidNumberOfArgumentsCommandTest() {
		CommandExecutor commandExecutor = new CommandExecutor();
		
		commandExecutor.executeCommand("LOAN IDIDI Dale 10000 5 4 abc");
	}
	
	@Test
	public void executeCommandIlegalArgumentExecptionTest() {
		CommandExecutor commandExecutor = new CommandExecutor();
		
		commandExecutor.executeCommand("LOAN IDIDI Dale 10000 5 ab");
	}
}
