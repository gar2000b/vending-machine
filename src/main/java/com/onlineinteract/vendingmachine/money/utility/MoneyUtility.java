package com.onlineinteract.vendingmachine.money.utility;

import java.util.List;

import com.onlineinteract.vendingmachine.model.money.FiftyPence;
import com.onlineinteract.vendingmachine.model.money.Pound;
import com.onlineinteract.vendingmachine.model.money.TenPence;
import com.onlineinteract.vendingmachine.model.money.TwentyPence;
import com.onlineinteract.vendingmachine.model.money.api.ValidCoin;

public class MoneyUtility {

	public static int getNumberOfPoundCoins(List<ValidCoin> changeList) {
		int count = 0;
		for (ValidCoin validCoin : changeList) {
			if (validCoin instanceof Pound) {
				count++;
			}
		}
		return count;
	}

	public static int getNumberOfFiftyPenceCoins(List<ValidCoin> changeList) {
		int count = 0;
		for (ValidCoin validCoin : changeList) {
			if (validCoin instanceof FiftyPence) {
				count++;
			}
		}
		return count;
	}

	public static int getNumberOfTwentyPenceCoins(List<ValidCoin> changeList) {
		int count = 0;
		for (ValidCoin validCoin : changeList) {
			if (validCoin instanceof TwentyPence) {
				count++;
			}
		}
		return count;
	}

	public static int getNumberOfTenPenceCoins(List<ValidCoin> changeList) {
		int count = 0;
		for (ValidCoin validCoin : changeList) {
			if (validCoin instanceof TenPence) {
				count++;
			}
		}
		return count;
	}
}
