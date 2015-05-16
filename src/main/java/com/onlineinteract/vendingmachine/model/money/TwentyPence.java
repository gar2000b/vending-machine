package com.onlineinteract.vendingmachine.model.money;

import java.math.BigDecimal;

import com.onlineinteract.vendingmachine.model.money.api.ValidCoin;

public class TwentyPence extends ValidCoin {
	public static final String NAME = "Twenty Pence";
	public static final BigDecimal FIXED_VALUE = new BigDecimal(0.20).setScale(2, BigDecimal.ROUND_HALF_EVEN);
	
	public TwentyPence() {
		super(NAME, FIXED_VALUE);
	}
}
