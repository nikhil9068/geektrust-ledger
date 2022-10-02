package com.example.ledger.utilities;

import com.example.ledger.entities.Loan;

import java.util.HashMap;
import java.util.Map;

public class Utilities {
	static Map<String, Map<String, Loan>> borrowerLoanMap = new HashMap<>();

    public static int getMonthsFromYears(int loanPeriod) {
        return loanPeriod * 12;
    }

    public static double calculateInterestAmount(double principalAmount, int loanPeriod, double interestRate) {
        return (principalAmount * loanPeriod * interestRate) / 100;
    }

    public static int calculateEMIAmount(double principalAmount, int totalMonths) {
        return (int) Math.ceil(principalAmount / totalMonths);
    }
    
    public static Map<String, Map<String, Loan>> getBorrowerLoanMap() {
        return borrowerLoanMap;
    }
}