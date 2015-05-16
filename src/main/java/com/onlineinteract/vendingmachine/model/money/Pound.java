package com.onlineinteract.vendingmachine.model.money;

import java.math.BigDecimal;

import com.onlineinteract.vendingmachine.model.money.api.ValidCoin;

public class Pound extends ValidCoin {
	public static final String NAME = "Pound";
	public static final BigDecimal FIXED_VALUE = new BigDecimal(1.00).setScale(2, BigDecimal.ROUND_HALF_EVEN);
	
	public Pound() {
		super(NAME, FIXED_VALUE);
	}
}
