package com.onlineinteract.vendingmachine.money;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.onlineinteract.vendingmachine.model.money.FiftyPence;
import com.onlineinteract.vendingmachine.model.money.Pound;
import com.onlineinteract.vendingmachine.model.money.TenPence;
import com.onlineinteract.vendingmachine.model.money.TwentyPence;
import com.onlineinteract.vendingmachine.model.money.api.ValidCoin;

public class Change {
	private int tenPenceCount;
	private int twentyPenceCount;
	private int fiftyPenceCount;
	private int poundCount;

	public Change(int tenPenceCount, int twentyPenceCount, int fiftyPenceCount,
			int poundCount) {
		super();
		this.tenPenceCount = tenPenceCount;
		this.twentyPenceCount = twentyPenceCount;
		this.fiftyPenceCount = fiftyPenceCount;
		this.poundCount = poundCount;
	}

	public List<ValidCoin> getChange(BigDecimal change) {
		List<ValidCoin> changeList = new ArrayList<ValidCoin>();

		change = poundsToChangeList(change, changeList);
		change = fiftyPencesToChangeList(change, changeList);
		change = twentyPencesToChangeList(change, changeList);
		tenPencesToChangeList(change, changeList);

		return changeList;
	}

	private void tenPencesToChangeList(BigDecimal change,
			List<ValidCoin> changeList) {
		int numTenPences = change.divide(TenPence.FIXED_VALUE).setScale(2)
				.intValue();
		if (tenPenceCount > numTenPences) {
			tenPenceCount--;
		} else {
			numTenPences = tenPenceCount;
			tenPenceCount = 0;
		}
		change = change.subtract(TenPence.FIXED_VALUE.multiply(new BigDecimal(
				numTenPences)));
		changeList.addAll(generateCoins(TenPence.class, numTenPences));
	}

	private BigDecimal twentyPencesToChangeList(BigDecimal change,
			List<ValidCoin> changeList) {
		int numTwentyPences = change.divide(TwentyPence.FIXED_VALUE)
				.setScale(2).intValue();
		if (twentyPenceCount > numTwentyPences) {
			twentyPenceCount--;
		} else {
			numTwentyPences = twentyPenceCount;
			twentyPenceCount = 0;
		}
		change = change.subtract(TwentyPence.FIXED_VALUE
				.multiply(new BigDecimal(numTwentyPences)));
		changeList.addAll(generateCoins(TwentyPence.class, numTwentyPences));
		return change;
	}

	private BigDecimal fiftyPencesToChangeList(BigDecimal change,
			List<ValidCoin> changeList) {
		int numFiftyPences = change.divide(FiftyPence.FIXED_VALUE).setScale(2)
				.intValue();
		if (fiftyPenceCount > numFiftyPences) {
			fiftyPenceCount--;
		} else {
			numFiftyPences = fiftyPenceCount;
			fiftyPenceCount = 0;
		}
		change = change.subtract(FiftyPence.FIXED_VALUE
				.multiply(new BigDecimal(numFiftyPences)));
		changeList.addAll(generateCoins(FiftyPence.class, numFiftyPences));
		return change;
	}

	private BigDecimal poundsToChangeList(BigDecimal change,
			List<ValidCoin> changeList) {
		int numPounds = change.divide(Pound.FIXED_VALUE).setScale(2).intValue();
		if (poundCount > numPounds) {
			poundCount--;
		} else {
			numPounds = poundCount;
			poundCount = 0;
		}
		change = change.subtract(Pound.FIXED_VALUE.multiply(new BigDecimal(
				numPounds)));
		changeList.addAll(generateCoins(Pound.class, numPounds));
		return change;
	}

	private List<ValidCoin> generateCoins(Class<?> clazz, int qty) {
		List<ValidCoin> coinList = new ArrayList<ValidCoin>();

		for (int i = 0; i < qty; i++) {
			try {
				coinList.add((ValidCoin) clazz.newInstance());
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}

		return coinList;
	}

	public boolean checkChangeThresholds(int tenPenceThreshold,
			int twentyPenceThreshold, int fiftyPenceThreshold,
			int poundThreshold) {
		if(tenPenceCount < tenPenceThreshold) {
			return false;
		}
		if(twentyPenceCount < twentyPenceThreshold) {
			return false;
		}
		if(fiftyPenceCount < fiftyPenceThreshold) {
			return false;
		}
		if(poundCount < poundThreshold) {
			return false;
		}

		return true;
	}
	
	public void addToChange(ValidCoin coin) {
		if (coin instanceof Pound) {
			incrementPoundCount();
		} else if (coin instanceof FiftyPence) {
			incrementFiftyPenceCount();
		} else if (coin instanceof TwentyPence) {
			incrementTwentyPenceCount();
		} else if (coin instanceof TenPence) {
			incrementTenPenceCount();
		}
	}
	
	public void removeFromChange(ValidCoin coin) {
		if (coin instanceof Pound) {
			decrementPoundCount();
		} else if (coin instanceof FiftyPence) {
			decrementFiftyPenceCount();
		} else if (coin instanceof TwentyPence) {
			decrementTwentyPenceCount();
		} else if (coin instanceof TenPence) {
			decrementTenPenceCount();
		}
	}
	
	private void incrementTenPenceCount() {
		tenPenceCount++;
	}
	
	private void decrementTenPenceCount() {
		if(tenPenceCount != 0) {
			tenPenceCount--;
		}
	}
	
	private void incrementTwentyPenceCount() {
		twentyPenceCount++;
	}
	
	private void decrementTwentyPenceCount() {
		if(twentyPenceCount != 0) {
			twentyPenceCount--;
		}
	}
	
	private void incrementFiftyPenceCount() {
		fiftyPenceCount++;
	}
	
	private void decrementFiftyPenceCount() {
		if(fiftyPenceCount != 0) {
			fiftyPenceCount--;
		}
	}
	
	private void incrementPoundCount() {
		poundCount++;
	}
	
	private void decrementPoundCount() {
		if(poundCount != 0) {
			poundCount--;
		}
	}

}
