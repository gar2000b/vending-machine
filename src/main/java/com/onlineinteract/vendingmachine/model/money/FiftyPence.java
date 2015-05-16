package com.onlineinteract.vendingmachine.model.money;

import java.math.BigDecimal;

import com.onlineinteract.vendingmachine.model.money.api.ValidCoin;

public class FiftyPence extends ValidCoin {
	public static final String NAME = "Fifty Pence";
	public static final BigDecimal FIXED_VALUE = new BigDecimal(0.50).setScale(2, BigDecimal.ROUND_HALF_EVEN);
	
	public FiftyPence() {
		super(NAME, FIXED_VALUE);
	}
}
