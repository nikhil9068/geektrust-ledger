package com.example.ledger.balance;

import com.example.ledger.command.Command;
import com.example.ledger.entities.Loan;
import com.example.ledger.entities.Payment;
import com.example.ledger.exceptions.InvalidNumberOfArgumentsException;
import com.example.ledger.utilities.Utilities;

import lombok.ToString;

import java.util.List;
import java.util.Map;

@ToString
public class BalanceExecutor implements Command {
	String[] inputParams;
    BalanceProcessor balanceProcessor;

    public BalanceExecutor(String[] inputParams) {
        this.inputParams = inputParams;
    }

    public void execute() throws IllegalArgumentException, InvalidNumberOfArgumentsException {
    	String bankName = "";
    	String borrowerName = "";
    	int completedEmiNumber;
    	
    	System.out.println("[INFO]: Executing BALANCE Command");
    	
    	if(inputParams.length == 4) {
    		try {
    			bankName = inputParams[1];                             // get bank name from CLI
    	        borrowerName = inputParams[2];                         // get borrower name from CLI
    	        completedEmiNumber = Integer.parseInt(inputParams[3]); // get completed EMI number from CLI
    		} catch (NumberFormatException e) {
    			throw new IllegalArgumentException(e.getMessage());
    		}
    	} else {
    		throw new InvalidNumberOfArgumentsException("Number of parameters passed: " + 
					String.valueOf(inputParams.length));
    	}
    	
        balanceProcessor = new BalanceProcessor(bankName, borrowerName, completedEmiNumber);
        processDisplayBalance(bankName, borrowerName, completedEmiNumber);
    }

    private void processDisplayBalance(String bankName, String borrowerName, int completedEmiNumber) {
        Map<String, Loan> bankLoanMap = Utilities.getBorrowerLoanMap().get(borrowerName);
        Loan borrowerLoan = bankLoanMap.get(bankName);
        
        List<Payment> paymentList = borrowerLoan.getPaymentList();
        
        paymentList = balanceProcessor.updatePayments(paymentList, completedEmiNumber, borrowerLoan.getAmountPerEmi(), borrowerLoan.getTotalAmount());
        
        borrowerLoan.setPaymentList(paymentList);
        
        bankLoanMap.put(bankName, borrowerLoan);
        Utilities.getBorrowerLoanMap().get(borrowerName).put(bankName, borrowerLoan);
        
        System.out.println("[INFO]: Displaying BALANCE Command Output");
        balanceProcessor.displayBalance(paymentList, completedEmiNumber, bankName, borrowerName);
    }
}