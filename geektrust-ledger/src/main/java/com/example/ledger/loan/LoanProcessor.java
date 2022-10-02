package com.example.ledger.loan;

import com.example.ledger.entities.Loan;
import com.example.ledger.entities.Payment;
import com.example.ledger.utilities.Utilities;

import lombok.AllArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@ToString
public class LoanProcessor {
	private String bankName;
	private String borrowerName;
	private double principalAmount;
	private int loanPeriod;
	private double interestRate;
    
    public void processLoan() {
    	List<Payment> paymentList = initializePaymentList();
    	System.out.println("[INFO]: Initialized payment list " + paymentList);
    	
    	int totalMonths = Utilities.getMonthsFromYears(loanPeriod);
        double interestAmount = Utilities.calculateInterestAmount(principalAmount, loanPeriod, interestRate);
        
        double totalAmount = principalAmount + interestAmount;
        
        int emiAmount = Utilities.calculateEMIAmount(totalAmount, totalMonths);
        
        Loan loan = new Loan(principalAmount, interestAmount, totalAmount, totalMonths, emiAmount, paymentList);
        System.out.println("[INFO]: Created Loan: " + loan);
        updateLoanMap(loan);
    }
    
    private List<Payment> initializePaymentList() {
    	List<Payment> paymentList = new ArrayList<Payment>();
    	paymentList.add(new Payment(0.0 , Utilities.getMonthsFromYears(loanPeriod)));
    	
    	return paymentList;
    }
    
    private void updateLoanMap(Loan loan) {
    	if(Utilities.getBorrowerLoanMap().containsKey(borrowerName)) {
        	Map<String, Loan> existingBankLoanMap = Utilities.getBorrowerLoanMap().get(borrowerName);
            
            existingBankLoanMap.put(bankName, loan);
            
            Utilities.getBorrowerLoanMap().put(borrowerName, existingBankLoanMap);
        } else {
        	Map<String,Loan> bankLoanMap = new HashMap<>();
            bankLoanMap.put(bankName, loan);
            
            Utilities.getBorrowerLoanMap().put(borrowerName, bankLoanMap);
        }
    }
}