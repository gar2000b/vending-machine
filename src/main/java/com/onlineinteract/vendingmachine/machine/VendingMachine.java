package com.onlineinteract.vendingmachine.machine;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.onlineinteract.vendingmachine.model.items.Item;
import com.onlineinteract.vendingmachine.model.money.api.ValidCoin;
import com.onlineinteract.vendingmachine.money.Change;
import com.onlineinteract.vendingmachine.money.InsertedMoney;

/**
 * Encapsulates the state of a vending machine and the operations that can be
 * performed on it
 */
public class VendingMachine {

	private boolean running;
	private List<Item> items = new ArrayList<>();
	private InsertedMoney insertedMoney = new InsertedMoney();
	private Change change;

	public VendingMachine() {
		super();

		initialiseItems();
		initialiseChange();
		checkChangeThresholds();
	}

	public boolean isOn() {
		return running;
	}

	public void setOn() {
		running = true;
	}

	public void setOff() {
		running = false;
	}

	public void insertCoin(ValidCoin validCoin) {
		insertedMoney.insertCoin(validCoin);
		change.addToChange(validCoin);
	}

	public List<ValidCoin> coinReturn() {
		List<ValidCoin> coinReturn = insertedMoney.coinReturn();
		for (ValidCoin validCoin : coinReturn) {
			change.removeFromChange(validCoin);
		}

		return coinReturn;
	}

	public boolean vend(char selector) throws Exception {
		// Check machine is running.
		if (!running) {
			coinReturn();
			throw new Exception("Vending Machine not running, returning money!");
		}

		Item item = getItem(selector);
		BigDecimal totalValue = insertedMoney.getTotal();

		// Check there are sufficient funds to vend item.
		if (totalValue.compareTo(item.getPrice()) < 0) {
			throw new Exception(
					"Insufficient Funds to vend this item. Please enter more funds and try again.");
		}

		// Check the select item is available.
		if (item.getCount() <= 0) {
			throw new Exception("Insufficient qty of item. Qty = "
					+ item.getCount());
		}

		// Vend Item and check to see if change is required.
		item.decrementCount();
		BigDecimal totalChange = totalValue.subtract(item.getPrice());
		List<ValidCoin> changeList = change.getChange(totalChange);

		// Output vend information to screen
		System.out.println("*** You selected item " + item.getSelector()
				+ "\nPlease collect your Item: '" + item.getName()
				+ "' from the tray below.");
		System.out.println("You inserted a cash total of: " + totalValue
				+ " and your change = " + totalChange + ".\n");
		if (changeList.size() > 0) {
			System.out.println("Your change is broken down as:");
			for (ValidCoin validCoin : changeList) {
				System.out.println(validCoin.getName());
			}
			System.out
					.println("\nPlease collect your change from the coin return drawer and have a nice day :)");
		} else {
			System.out.println("Have a nice day :)\n");
		}

		checkChangeThresholds();
		insertedMoney.vendComplete();

		return true;
	}

	private Item getItem(char selector) {
		for (Item item : items) {
			if (item.getSelector() == selector) {
				return item;
			}
		}
		return null;
	}

	// TODO: one future enhancement could be that we have a fixed data store to
	// load and persist these items during application runtime / initialisation.
	private void initialiseItems() {
		Item cokeCan = new Item("Can of Coke", 10,
				new BigDecimal(0.60).setScale(2, BigDecimal.ROUND_HALF_EVEN),
				'A');
		Item crispsPacketOf = new Item("Packet of Crisps", 8, new BigDecimal(
				1.00).setScale(2, BigDecimal.ROUND_HALF_EVEN), 'B');
		Item sandwichesPackOf = new Item("Pack of Sandwiches", 12,
				new BigDecimal(1.70).setScale(2, BigDecimal.ROUND_HALF_EVEN),
				'C');

		items.add(cokeCan);
		items.add(crispsPacketOf);
		items.add(sandwichesPackOf);
	}

	// TODO: one future enhancement could be that we have a fixed data store to
	// load and persist this change object during application runtime /
	// initialisation.
	private void initialiseChange() {
		change = new Change(100, 90, 70, 80);
	}

	// TODO: Note: these are arbitrary thresholds against each of the coin
	// stock. if we fall below one of these thresholds, then render 'Please use
	// exact change' message... These could come from a config.
	private void checkChangeThresholds() {
		boolean changeThresholds = change.checkChangeThresholds(10, 10, 10, 10);
		if (!changeThresholds) {
			System.out
					.println("Please use exact change as change may not be given. Thank you!");
		}
	}

	// Setter injection used for test purposes only.
	public void setItems(List<Item> items) {
		this.items = items;
	}
}
