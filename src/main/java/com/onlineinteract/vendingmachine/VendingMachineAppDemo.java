package com.onlineinteract.vendingmachine;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.onlineinteract.vendingmachine.machine.VendingMachine;
import com.onlineinteract.vendingmachine.model.items.Item;
import com.onlineinteract.vendingmachine.model.money.FiftyPence;
import com.onlineinteract.vendingmachine.model.money.Pound;
import com.onlineinteract.vendingmachine.model.money.TenPence;
import com.onlineinteract.vendingmachine.model.money.TwentyPence;
import com.onlineinteract.vendingmachine.model.money.api.ValidCoin;

public class VendingMachineAppDemo {

	private VendingMachine vendingMachine;

	public VendingMachineAppDemo() {
		vendingMachine = new VendingMachine();
	}

	public void demo1() {
		System.out
				.println("\n*** Welcome to Demo 1 where we insert 60p and vend a can of Coke which also costs 60p. ***\n");
		vendingMachine.setOn();
		insertSixtyPence();

		try {
			vendingMachine.vend('A');
		} catch (Exception e) {
			System.out.println("### Sorry, there was a problem. ("
					+ e.getMessage() + ")");
		}
	}

	public void demo2() {
		System.out
				.println("\n*** Welcome to Demo 2 where we insert 50p and try to vend a can of Coke which costs 60p. ***\n");
		vendingMachine.setOn();
		insertFiftyPence();

		try {
			vendingMachine.vend('A');
		} catch (Exception e) {
			System.out.println("### Sorry, there was a problem. ("
					+ e.getMessage() + ")");
		}
	}
	
	public void demo3() {
		System.out
				.println("\n*** Welcome to Demo 3 where we insert £1.80p and immediately request coin return. ***\n");
		vendingMachine.setOn();
		insertOnePoundEighty();

		List<ValidCoin> coinReturn = vendingMachine.coinReturn();
		System.out.println("Your coin return is broken down as:");
		for (ValidCoin validCoin : coinReturn) {
			System.out.println(validCoin.getName());
		}
	}
	
	public void demo4() {
		System.out
				.println("\n*** Welcome to Demo 4 where we insert £1.80p and vend a can of Coke expecting change. ***\n");
		vendingMachine.setOn();
		insertOnePoundEighty();

		try {
			vendingMachine.vend('A');
		} catch (Exception e) {
			System.out.println("### Sorry, there was a problem. ("
					+ e.getMessage() + ")");
		}
	}
	
	public void demo5() {
		System.out
				.println("\n*** Welcome to Demo 5 where we insert £1.80p and vend a packet of crisps expecting change. ***\n");
		vendingMachine.setOn();
		insertOnePoundEighty();

		try {
			vendingMachine.vend('B');
		} catch (Exception e) {
			System.out.println("### Sorry, there was a problem. ("
					+ e.getMessage() + ")");
		}
	}
	
	public void demo6() {
		System.out
				.println("\n*** Welcome to Demo 6 where we try to vend from a machine not running. ***\n");
		vendingMachine.setOff();
		insertOnePoundEighty();

		try {
			vendingMachine.vend('B');
		} catch (Exception e) {
			System.out.println("### Sorry, there was a problem. ("
					+ e.getMessage() + ")");
		}
	}
	
	public void demo7() {
		System.out
				.println("\n*** Welcome to Demo 7 where we try to vend an item that is empty. ***\n");
		vendingMachine.setOn();
		insertOnePoundEighty();
		injectItemWithZeroQty();

		try {
			vendingMachine.vend('A');
		} catch (Exception e) {
			System.out.println("### Sorry, there was a problem. ("
					+ e.getMessage() + ")");
		}
	}

	public static void main(String[] args) throws Exception {
		VendingMachineAppDemo vendingMachineAppDemo = new VendingMachineAppDemo();
		vendingMachineAppDemo.demo1();
		seperator();
		vendingMachineAppDemo.demo2();
		vendingMachineAppDemo.vendingMachine.coinReturn(); // manually flushing coins.
		seperator();
		vendingMachineAppDemo.demo3();
		seperator();
		vendingMachineAppDemo.demo4();
		seperator();
		vendingMachineAppDemo.demo5();
		seperator();
		vendingMachineAppDemo.demo6();
		seperator();
		vendingMachineAppDemo.demo7();
		vendingMachineAppDemo.vendingMachine.coinReturn(); // manually flushing coins.
		seperator();
	}
	
	private static void seperator() {
		System.out.println("\n--------------------------------------------\n");
	}

	private void insertSixtyPence() {
		vendingMachine.insertCoin(new FiftyPence());
		vendingMachine.insertCoin(new TenPence());
	}

	private void insertFiftyPence() {
		vendingMachine.insertCoin(new FiftyPence());
	}
	
	private void insertOnePoundEighty() {
		vendingMachine.insertCoin(new Pound());
		vendingMachine.insertCoin(new FiftyPence());
		vendingMachine.insertCoin(new TwentyPence());
		vendingMachine.insertCoin(new TenPence());
	}
	
	private void injectItemWithZeroQty() {
		List<Item> items = new ArrayList<>();
		Item cokeCan = new Item("Can of Coke", 0,
				new BigDecimal(0.60).setScale(2, BigDecimal.ROUND_HALF_EVEN),
				'A');
		items.add(cokeCan);
		vendingMachine.setItems(items);
	}
}
