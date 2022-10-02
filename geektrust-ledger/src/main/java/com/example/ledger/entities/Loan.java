package com.example.ledger.entities;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class Loan {
	private double principalAmount;
    private double interestAmount;
    private double totalAmount;
    private int emiMonths;
    private int amountPerEmi;
    private List<Payment> paymentList;
}