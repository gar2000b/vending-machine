package com.onlineinteract.vendingmachine.model.items;

import java.math.BigDecimal;

public class Item {

	private String name;
	private int count;
	private BigDecimal price;
	private char selector;

	public Item(String name, int count, BigDecimal price, char selector) {
		super();
		this.name = name;
		this.count = count;
		this.price = price;
		this.selector = selector;
	}

	public void decrementCount() throws Exception {
		if (count > 0) {
			count--;
		} else {
			throw new Exception("Count is already at zero for " + name
					+ ", cannot decrement");
		}
	}

	public String getName() {
		return name;
	}

	public int getCount() {
		return count;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public char getSelector() {
		return selector;
	}
}
