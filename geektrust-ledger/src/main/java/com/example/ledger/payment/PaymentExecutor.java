package com.example.ledger.payment;

import com.example.ledger.command.Command;
import com.example.ledger.entities.Loan;
import com.example.ledger.entities.Payment;
import com.example.ledger.exceptions.InvalidNumberOfArgumentsException;
import com.example.ledger.utilities.Utilities;

import lombok.ToString;

import java.util.List;
import java.util.Map;

@ToString
public class PaymentExecutor implements Command {
	private String[] inputParams;
    private PaymentProcessor paymentProcessor;

    public PaymentExecutor(String[] inputParams) {
        this.inputParams = inputParams;
    }

    public void execute() throws IllegalArgumentException, InvalidNumberOfArgumentsException {
        String bankName = null;
        String borrowerName = null;
        double lumpSumAmount;
        int completedEmiNumber;
        
        System.out.println("[INFO]: Executing PAYMENT Command");
        
        if(inputParams.length == 5) {
        	try {
        		bankName = inputParams[1];                             // get bank name from CLI
                borrowerName = inputParams[2];                         // get borrower name from CLI
                lumpSumAmount = Double.parseDouble(inputParams[3]);    // get lumpSumAmount from CLI
                completedEmiNumber = Integer.parseInt(inputParams[4]); // get after EMI Count from CLI
        	} catch (NumberFormatException e) {
    			throw new IllegalArgumentException(e.getMessage());
    		}
        } else {
        	throw new InvalidNumberOfArgumentsException("Number of parameters passed: " + 
					String.valueOf(inputParams.length));
        }
    	
        paymentProcessor = new PaymentProcessor(bankName, borrowerName, lumpSumAmount, completedEmiNumber);
        
        executePayment(bankName, borrowerName, lumpSumAmount, completedEmiNumber);
    }

    private void executePayment(String bankName, String borrowerName, double lumpSumAmount, int completedEmiNumber) {
        Map<String, Loan> bankLoanMap = Utilities.getBorrowerLoanMap().get(borrowerName);
        
        Loan borrowerLoan = bankLoanMap.get(bankName);
        
        List<Payment> paymentList = borrowerLoan.getPaymentList();
        
        System.out.println("[INFO]: Processing the Payment");
        paymentList = paymentProcessor.updatePayments(paymentList, lumpSumAmount, completedEmiNumber, borrowerLoan.getAmountPerEmi(), borrowerLoan.getTotalAmount());
        
        borrowerLoan.setPaymentList(paymentList);
        
        bankLoanMap.put(bankName, borrowerLoan);
        Utilities.getBorrowerLoanMap().get(borrowerName).put(bankName, borrowerLoan);
    }
}
