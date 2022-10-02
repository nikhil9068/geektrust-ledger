package com.example.ledger.loan;

import org.junit.jupiter.api.Test;

public class LoanProcessorTest {
	@Test
	public void processLoanPositiveTest() {
		LoanProcessor loanProcessor = new LoanProcessor("IDIDI", "Dale", 10000.0, 5, 6.0);
		
		loanProcessor.processLoan();
	}
	
	// No negative test as no exception data would reach LoanProcessor object
}