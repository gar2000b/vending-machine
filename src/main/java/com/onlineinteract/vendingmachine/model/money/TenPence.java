package com.onlineinteract.vendingmachine.model.money;

import java.math.BigDecimal;

import com.onlineinteract.vendingmachine.model.money.api.ValidCoin;

public class TenPence extends ValidCoin {
	public static final String NAME = "Ten Pence";
	public static final BigDecimal FIXED_VALUE = new BigDecimal(0.10).setScale(2, BigDecimal.ROUND_HALF_EVEN);
	
	public TenPence() {
		super(NAME, FIXED_VALUE);
	}
}
