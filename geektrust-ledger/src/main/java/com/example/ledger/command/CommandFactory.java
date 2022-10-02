package com.example.ledger.command;

import java.util.Arrays;
import java.util.Optional;

import com.example.ledger.enums.ValidCommands;
import com.example.ledger.balance.BalanceExecutor;
import com.example.ledger.exceptions.UnrecognizedCommandException;
import com.example.ledger.loan.LoanExecutor;
import com.example.ledger.payment.PaymentExecutor;

public class CommandFactory {
	public Optional<Command> getInstance(String command, String[] inputParams) throws UnrecognizedCommandException {
		Command commandExecutor;
        
        if(command.equals(ValidCommands.LOAN.getValue())) {
        	commandExecutor = new LoanExecutor(inputParams);
        } else if(command.equals(ValidCommands.PAYMENT.getValue())) {
        	commandExecutor = new PaymentExecutor(inputParams);
        } else if(command.equals(ValidCommands.BALANCE.getValue())) {
        	commandExecutor = new BalanceExecutor(inputParams);
        } else {
        	throw new UnrecognizedCommandException("Illegal Command, valid commands are: " + 
        			Arrays.toString(ValidCommands.values()) , command);
        }
        
        System.out.println("[INFO]: Generated Command Object");
        
        return Optional.of(commandExecutor);
	}
}