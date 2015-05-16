package com.onlineinteract.vendingmachine.money;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.onlineinteract.vendingmachine.model.money.api.ValidCoin;

public class InsertedMoney {
	private List<ValidCoin> money = new ArrayList<ValidCoin>();

	public void insertCoin(ValidCoin validCoin) {
		money.add(validCoin);
	}

	public List<ValidCoin> coinReturn() {
		List<ValidCoin> tmpMoneyReturn = new ArrayList<ValidCoin>(money);
		money.clear();
		return tmpMoneyReturn;
	}
	
	public BigDecimal getTotal() {
		BigDecimal totalValue = new BigDecimal(0.00).setScale(2,
				BigDecimal.ROUND_HALF_EVEN);
		for (ValidCoin validCoin : money) {
			totalValue = totalValue.add(validCoin.getValue());
		}
		
		return totalValue;
	}
	
	public void vendComplete() {
		money.clear();
	}
}
