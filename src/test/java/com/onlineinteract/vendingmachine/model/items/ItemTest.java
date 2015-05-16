package com.onlineinteract.vendingmachine.model.items;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

public class ItemTest {

	@Test
	public void shouldDecrementItemsCount() throws Exception {
		Item cokeCan = new Item("Can of Coke", 10,
				new BigDecimal(0.60).setScale(2, BigDecimal.ROUND_HALF_EVEN),
				'A');

		cokeCan.decrementCount();
		cokeCan.decrementCount();
		cokeCan.decrementCount();

		assertTrue(cokeCan.getCount() == 7);
	}

	@Test(expected = Exception.class)
	public void shouldThrowExceptionIndicatingCountIsAlreadyAtZero()
			throws Exception {
		Item cokeCan = new Item("Can of Coke", 2,
				new BigDecimal(0.60).setScale(2, BigDecimal.ROUND_HALF_EVEN),
				'A');

		cokeCan.decrementCount();
		cokeCan.decrementCount();
		cokeCan.decrementCount();
	}
}
