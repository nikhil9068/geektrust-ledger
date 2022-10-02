package com.example.ledger.command;

import java.util.Optional;

import com.example.ledger.exceptions.InvalidNumberOfArgumentsException;
import com.example.ledger.exceptions.UnrecognizedCommandException;

public class CommandExecutor {
	private Command commandToExecute;
	private Optional<Command> commandInstance;
	
	public void executeCommand(String inputFileLine) throws NullPointerException, RuntimeException {
		if(inputFileLine.equals(null) || inputFileLine.equals("")) {
			throw new NullPointerException("Empty Line in File");
		} else if(inputFileLine.contains("\t")) {
			throw new RuntimeException("No tab should be present in the command line");
		} else {
			String[] inputParams = inputFileLine.split(" ");
			String operation = inputParams[0];
			
			CommandFactory commandFactory = new CommandFactory();
			
			try {
				// get instance of commandExecutor according to the type of command from CommandFactory
				commandInstance = commandFactory.getInstance(operation, inputParams);
				
				commandInstance.ifPresent(command -> {
						System.out.println("[INFO]: Command in executor: " + command);
						commandToExecute = command;
					}
				);
				
				// execute the command encountered: Loan, Balance, Payment
				commandToExecute.execute();
			} catch (UnrecognizedCommandException e) {
				System.out.println("[Exception]: " + e.getMessage());
			} catch (IllegalArgumentException e) {
				System.out.println("[Exception]: " + e.getMessage());
			} catch (InvalidNumberOfArgumentsException e) {
				System.out.println("[Exception]: " + e.getMessage());
			}
		}
	}
}