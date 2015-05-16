package com.onlineinteract.vendingmachine.machine;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.onlineinteract.vendingmachine.model.items.Item;
import com.onlineinteract.vendingmachine.model.money.FiftyPence;
import com.onlineinteract.vendingmachine.model.money.Pound;
import com.onlineinteract.vendingmachine.model.money.TenPence;
import com.onlineinteract.vendingmachine.model.money.TwentyPence;
import com.onlineinteract.vendingmachine.model.money.api.ValidCoin;

/**
 * Unit tests for {@link VendingMachine}
 */
public class VendingMachineTest {

	private VendingMachine machine;

	@Before
	public void setup() {
		machine = new VendingMachine();
		machine.setOn();
	}

	@Test
	public void defaultStateIsOff() {
		machine = new VendingMachine();
		assertFalse(machine.isOn());
	}

	@Test
	public void shouldTurnMachineOn() {
		assertTrue(machine.isOn());
	}

	@Test
	public void shouldTurnMachineOff() {
		machine.setOff();
		assertFalse(machine.isOn());
	}

	@Test
	public void shouldInsertAndReturnCoinsTotallingOnePoundEightyPence() {
		insertOnePoundEightyPence();
		
		List<ValidCoin> coinReturn = machine.coinReturn();
		BigDecimal totalValue = new BigDecimal(0.00).setScale(2,
				BigDecimal.ROUND_HALF_EVEN);
		for (ValidCoin validCoin : coinReturn) {
			totalValue = totalValue.add(validCoin.getValue());
		}

		assertTrue(totalValue.equals(new BigDecimal(1.80).setScale(2,
				BigDecimal.ROUND_HALF_EVEN)));
	}
	
	@Test
	public void shouldInsertAndReturnCoinsTwiceYieldingNoCoinsReturned() {
		insertOnePoundEightyPence();
		
		List<ValidCoin> coinReturn = machine.coinReturn();
		coinReturn = machine.coinReturn();
		
		assertTrue(coinReturn.size() == 0);
	}
	
	@Test
	public void shouldReturnCoinsYieldingNoCoinsReturned() {
		List<ValidCoin> coinReturn = machine.coinReturn();
		
		assertTrue(coinReturn.size() == 0);
	}
	
	@Test
	public void shouldVendItemWithExactMoneyInserted() throws Exception {
		insertSixtyPence();
		
		boolean success = machine.vend('A');
		assertTrue(success == true);
	}
	
	@Test
	public void shouldVendItemWithAdditionalMoneyInserted() throws Exception {
		insertOnePoundEightyPence();
		
		boolean success = machine.vend('A');
		assertTrue(success == true);
	}
	
	@Test(expected=Exception.class)
	public void shouldThrowExceptionAndNotVendItemWithInsufficientFundsProvided() throws Exception {
		insertFiftyPence();
		
		machine.vend('A');
	}
	
	@Test(expected=Exception.class)
	public void shouldThrowExceptionAndNotVendItemWithMachineNotRunning() throws Exception {
		machine.setOff();
		insertOnePoundEightyPence();
		
		machine.vend('A');
	}
	
	@Test(expected=Exception.class)
	public void shouldThrowExceptionAndNotVendItemWithInsufficientItemQty() throws Exception {
		// Note: in order to achieve this, we have to stub and setter inject the items. Ideally,
		// we would use a mocking framework like mockito but would prefer to have no library dependencies
		// against this project.
		injectItemWithZeroQty();
		
		insertOnePoundEightyPence();
		
		machine.vend('A');
	}
	
	private void insertOnePoundEightyPence() {
		machine.insertCoin(new Pound());
		machine.insertCoin(new FiftyPence());
		machine.insertCoin(new TwentyPence());
		machine.insertCoin(new TenPence());
	}
	
	private void insertSixtyPence() {
		machine.insertCoin(new FiftyPence());
		machine.insertCoin(new TenPence());
	}
	
	private void insertFiftyPence() {
		machine.insertCoin(new FiftyPence());
	}
	
	private void injectItemWithZeroQty() {
		List<Item> items = new ArrayList<>();
		Item cokeCan = new Item("Can of Coke", 0,
				new BigDecimal(0.60).setScale(2, BigDecimal.ROUND_HALF_EVEN),
				'A');
		items.add(cokeCan);
		machine.setItems(items);
	}
}
