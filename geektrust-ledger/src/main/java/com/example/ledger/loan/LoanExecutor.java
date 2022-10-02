package com.example.ledger.loan;

import com.example.ledger.command.Command;
import com.example.ledger.exceptions.InvalidNumberOfArgumentsException;

import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class LoanExecutor implements Command {
	String[] inputParams;
    
    public void execute() throws IllegalArgumentException, InvalidNumberOfArgumentsException {
    	String bankName = null;
        String borrowerName = null;
        double principalAmount;
        int loanPeriod;
        double interestRate;
        
        System.out.println("[INFO]: Executing LOAN Command");
        
        if(inputParams.length == 6) {
        	try {
    	    	bankName = inputParams[1];                            // bank name from CLI
    	        borrowerName = inputParams[2];                        // borrower name from CLI
    	        principalAmount = Double.parseDouble(inputParams[3]); // principal amount from CLI
    	        loanPeriod = Integer.parseInt(inputParams[4]);        // number of years from CLI
    	        interestRate = Double.parseDouble(inputParams[5]);    // rate of interest from CLI
            } catch (NumberFormatException e) {
    			throw new IllegalArgumentException(e.getMessage());
    		}
        } else {
        	throw new InvalidNumberOfArgumentsException("Number of parameters passed: " + 
        												inputParams.length);
        }
        
        // execute the loan command
        executeLoan(bankName, borrowerName, principalAmount, loanPeriod, interestRate);
    }
    
    private void executeLoan(String bankName, String borrowerName, double principalAmount, int loanPeriod, double interestRate) {
    	LoanProcessor loanProcessor = new LoanProcessor(bankName, borrowerName, principalAmount, loanPeriod, interestRate);
        
    	System.out.println("[INFO]: Processing the loan");
        // process the loan
        loanProcessor.processLoan();
    }
}