package com.onlineinteract.vendingmachine.money;


import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.onlineinteract.vendingmachine.model.money.FiftyPence;
import com.onlineinteract.vendingmachine.model.money.Pound;
import com.onlineinteract.vendingmachine.model.money.TenPence;
import com.onlineinteract.vendingmachine.model.money.TwentyPence;
import com.onlineinteract.vendingmachine.model.money.api.ValidCoin;
import com.onlineinteract.vendingmachine.money.utility.MoneyUtility;

public class InsertedMoneyTest {

	private InsertedMoney insertedMoney;

	@Before
	public void setup() {
		insertedMoney = new InsertedMoney();
	}
	
	@Test
	public void shouldInsertMoneyTotallingOnePoundEighty() {
		insertOnePoundEightyPence();
		BigDecimal total = insertedMoney.getTotal();
		assertTrue(total.equals(new BigDecimal(1.80).setScale(2, BigDecimal.ROUND_HALF_EVEN)));
	}
	
	@Test
	public void shouldReturnMoneyTotallingOnePoundEighty() {
		insertOnePoundEightyPence();
		List<ValidCoin> coinReturn = insertedMoney.coinReturn();
		
		assertTrue(MoneyUtility.getNumberOfPoundCoins(coinReturn) == 1);
		assertTrue(MoneyUtility.getNumberOfFiftyPenceCoins(coinReturn) == 1);
		assertTrue(MoneyUtility.getNumberOfTwentyPenceCoins(coinReturn) == 1);
		assertTrue(MoneyUtility.getNumberOfTenPenceCoins(coinReturn) == 1);
	}
	
	@Test
	public void shouldInsertAndCallReturnCoinsTwiceYieldingNoCoinsReturned() {
		insertOnePoundEightyPence();
		insertedMoney.coinReturn();
		List<ValidCoin> coinReturn = insertedMoney.coinReturn();
		
		assertTrue(MoneyUtility.getNumberOfPoundCoins(coinReturn) == 0);
		assertTrue(MoneyUtility.getNumberOfFiftyPenceCoins(coinReturn) == 0);
		assertTrue(MoneyUtility.getNumberOfTwentyPenceCoins(coinReturn) == 0);
		assertTrue(MoneyUtility.getNumberOfTenPenceCoins(coinReturn) == 0);
	}
	
	public void insertOnePoundEightyPence() {
		insertedMoney.insertCoin(new Pound());
		insertedMoney.insertCoin(new FiftyPence());
		insertedMoney.insertCoin(new TwentyPence());
		insertedMoney.insertCoin(new TenPence());
	}
}
