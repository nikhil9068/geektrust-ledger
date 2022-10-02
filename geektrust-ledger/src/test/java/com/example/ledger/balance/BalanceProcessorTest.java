package com.example.ledger.balance;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.example.ledger.entities.Loan;
import com.example.ledger.entities.Payment;
import com.example.ledger.exceptions.InvalidNumberOfArgumentsException;
import com.example.ledger.loan.LoanExecutor;
import com.example.ledger.utilities.Utilities;

public class BalanceProcessorTest {
	@Test
	public void updatePaymentBalancePositiveTest() throws IllegalArgumentException, InvalidNumberOfArgumentsException {
		String[] inputParams1 = {"LOAN", "IDIDI", "Dale", "5000", "1", "6"};
		LoanExecutor loanExecutor = new LoanExecutor(inputParams1);
		loanExecutor.execute();
		
		String bankName = "IDIDI";
		String borrowerName = "Dale";
		int completedEmiNumber = 5;
		
		BalanceProcessor balanceProcessor = new BalanceProcessor(bankName, borrowerName, completedEmiNumber);
		
		Map<String, Loan> bankLoanMap = Utilities.getBorrowerLoanMap().get(borrowerName);
        
        Loan borrowerLoan = bankLoanMap.get(bankName);
        
        List<Payment> paymentList = borrowerLoan.getPaymentList();
		
        List<Payment> pl = balanceProcessor.updatePayments(paymentList, completedEmiNumber, borrowerLoan.getAmountPerEmi(), borrowerLoan.getTotalAmount());
        
        assertEquals(paymentList.get(paymentList.size()-1).getEmiMonthsLeft(), pl.get(pl.size()-1).getEmiMonthsLeft());
	}
}