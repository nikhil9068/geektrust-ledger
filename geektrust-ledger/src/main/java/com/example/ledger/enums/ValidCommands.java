package com.example.ledger.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ValidCommands {
	LOAN("LOAN"),
	PAYMENT("PAYMENT"),
	BALANCE("BALANCE");
	
	private final String value;
}