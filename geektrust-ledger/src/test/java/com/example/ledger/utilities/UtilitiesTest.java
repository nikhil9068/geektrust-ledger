package com.example.ledger.utilities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class UtilitiesTest {
	@Test
	public void getMonthsFromYearsTest() {
		int months = Utilities.getMonthsFromYears(13);
		
		assertEquals(13 * 12, months);
	}
	
	@Test
	public void calculateInterestAmountTest() {
		double interest = Utilities.calculateInterestAmount(1000, 1, 1);
		
		assertEquals(10.0, interest);
	}
	
	@Test
	public void calculateEmiAmount() {
		int emi = Utilities.calculateEMIAmount(1000, 10);
		
		assertEquals(100, emi);
	}
}