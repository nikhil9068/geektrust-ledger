package com.example.ledger.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class Payment {
	private  double amountPaid;
    private int emiMonthsLeft;
}