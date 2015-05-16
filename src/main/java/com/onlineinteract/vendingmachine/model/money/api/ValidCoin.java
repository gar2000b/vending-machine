package com.onlineinteract.vendingmachine.model.money.api;

import java.math.BigDecimal;

public abstract class ValidCoin {
	private String name;
	private BigDecimal value;
	
	public ValidCoin(String name, BigDecimal value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public BigDecimal getValue() {
		return value;
	}
}
