package com.example.ledger.payment;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.example.ledger.entities.Loan;
import com.example.ledger.entities.Payment;
import com.example.ledger.exceptions.InvalidNumberOfArgumentsException;
import com.example.ledger.loan.LoanExecutor;
import com.example.ledger.utilities.Utilities;

public class PaymentProcessorTest {
	@Test
	public void updatePaymentPositiveTest() throws IllegalArgumentException, InvalidNumberOfArgumentsException {
		String[] inputParams1 = {"LOAN", "IDIDI", "Dale", "5000", "1", "6"};
		LoanExecutor loanExecutor = new LoanExecutor(inputParams1);
		loanExecutor.execute();
		
		String bankName = "IDIDI";
		String borrowerName = "Dale";
		double lumpSumAmount = 1000.0;
		int completedEmiNumber = 5;
		
		PaymentProcessor paymentProcessor = new PaymentProcessor(bankName, borrowerName, lumpSumAmount, completedEmiNumber);
		
		Map<String, Loan> bankLoanMap = Utilities.getBorrowerLoanMap().get(borrowerName);
        
        Loan borrowerLoan = bankLoanMap.get(bankName);
        
        List<Payment> paymentList = borrowerLoan.getPaymentList();
		
		paymentProcessor.updatePayments(paymentList, lumpSumAmount, completedEmiNumber, borrowerLoan.getAmountPerEmi(), borrowerLoan.getTotalAmount());
	}
	
	@Test
	public void updateFirstPaymentPositiveTest() throws IllegalArgumentException, InvalidNumberOfArgumentsException {
		String[] inputParams1 = {"LOAN", "IDIDI", "Dale", "5000", "1", "6"};
		LoanExecutor loanExecutor = new LoanExecutor(inputParams1);
		loanExecutor.execute();
		
		String bankName = "IDIDI";
		String borrowerName = "Dale";
		double lumpSumAmount = 1000.0;
		int completedEmiNumber = 0;
		
		PaymentProcessor paymentProcessor = new PaymentProcessor(bankName, borrowerName, lumpSumAmount, completedEmiNumber);
		
		Map<String, Loan> bankLoanMap = Utilities.getBorrowerLoanMap().get(borrowerName);
        
        Loan borrowerLoan = bankLoanMap.get(bankName);
        
        List<Payment> paymentList = borrowerLoan.getPaymentList();
		
		paymentProcessor.updatePayments(paymentList, lumpSumAmount, completedEmiNumber, borrowerLoan.getAmountPerEmi(), borrowerLoan.getTotalAmount());
	}
}