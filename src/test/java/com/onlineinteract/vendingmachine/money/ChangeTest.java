package com.onlineinteract.vendingmachine.money;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.onlineinteract.vendingmachine.model.money.api.ValidCoin;
import com.onlineinteract.vendingmachine.money.utility.MoneyUtility;

public class ChangeTest {

	private Change change;

	@Before
	public void setup() {
		change = new Change(100, 90, 70, 80);
	}
	
	@Test
	public void shouldGetChangeOneOfEachCoin() {
		BigDecimal changeIn = new BigDecimal(1.80).setScale(2,
				BigDecimal.ROUND_HALF_EVEN);
		List<ValidCoin> changeList = change.getChange(changeIn);

		assertTrue(MoneyUtility.getNumberOfPoundCoins(changeList) == 1);
		assertTrue(MoneyUtility.getNumberOfFiftyPenceCoins(changeList) == 1);
		assertTrue(MoneyUtility.getNumberOfTwentyPenceCoins(changeList) == 1);
		assertTrue(MoneyUtility.getNumberOfTenPenceCoins(changeList) == 1);
	}
	
	@Test
	public void shouldGetNoChange() {
		BigDecimal changeIn = new BigDecimal(0.00).setScale(2,
				BigDecimal.ROUND_HALF_EVEN);
		List<ValidCoin> changeList = change.getChange(changeIn);

		assertTrue(MoneyUtility.getNumberOfPoundCoins(changeList) == 0);
		assertTrue(MoneyUtility.getNumberOfFiftyPenceCoins(changeList) == 0);
		assertTrue(MoneyUtility.getNumberOfTwentyPenceCoins(changeList) == 0);
		assertTrue(MoneyUtility.getNumberOfTenPenceCoins(changeList) == 0);
	}
	
	@Test
	public void shouldGetMultipleCoins() {
		BigDecimal changeIn = new BigDecimal(5.90).setScale(2,
				BigDecimal.ROUND_HALF_EVEN);
		List<ValidCoin> changeList = change.getChange(changeIn);

		assertTrue(MoneyUtility.getNumberOfPoundCoins(changeList) == 5);
		assertTrue(MoneyUtility.getNumberOfFiftyPenceCoins(changeList) == 1);
		assertTrue(MoneyUtility.getNumberOfTwentyPenceCoins(changeList) == 2);
		assertTrue(MoneyUtility.getNumberOfTenPenceCoins(changeList) == 0);
	}

	@Test
	public void shouldGetNoChangeIfNoChangeAvailable() {
		change = new Change(0, 0, 0, 0);
		
		BigDecimal changeIn = new BigDecimal(5.90).setScale(2,
				BigDecimal.ROUND_HALF_EVEN);
		List<ValidCoin> changeList = change.getChange(changeIn);

		assertTrue(MoneyUtility.getNumberOfPoundCoins(changeList) == 0);
		assertTrue(MoneyUtility.getNumberOfFiftyPenceCoins(changeList) == 0);
		assertTrue(MoneyUtility.getNumberOfTwentyPenceCoins(changeList) == 0);
		assertTrue(MoneyUtility.getNumberOfTenPenceCoins(changeList) == 0);
	}
	
	@Test
	public void shouldGetWhateverChangeIsAvailable() {
		change = new Change(0, 0, 0, 2);
		
		BigDecimal changeIn = new BigDecimal(5.90).setScale(2,
				BigDecimal.ROUND_HALF_EVEN);
		List<ValidCoin> changeList = change.getChange(changeIn);

		assertTrue(MoneyUtility.getNumberOfPoundCoins(changeList) == 2);
		assertTrue(MoneyUtility.getNumberOfFiftyPenceCoins(changeList) == 0);
		assertTrue(MoneyUtility.getNumberOfTwentyPenceCoins(changeList) == 0);
		assertTrue(MoneyUtility.getNumberOfTenPenceCoins(changeList) == 0);
	}
	
	@Test
	public void shouldCheckChangeThresholdsAreAboveSetLevels() {
		change = new Change(10, 10, 10, 10);
		boolean checkChangeThresholds = change.checkChangeThresholds(10, 10, 10, 10);
		
		assertTrue(checkChangeThresholds);
	}
	
	@Test
	public void shouldCheckChangeThresholdsAreBelowSetLevelsForTenPence() {
		change = new Change(10, 10, 10, 10);
		boolean checkChangeThresholds = change.checkChangeThresholds(11, 10, 10, 10);
		
		assertFalse(checkChangeThresholds);
	}
	
	@Test
	public void shouldCheckChangeThresholdsAreBelowSetLevelsForTwentyPence() {
		change = new Change(10, 10, 10, 10);
		boolean checkChangeThresholds = change.checkChangeThresholds(10, 11, 10, 10);
		
		assertFalse(checkChangeThresholds);
	}
	
	@Test
	public void shouldCheckChangeThresholdsAreBelowSetLevelsForFiftyPence() {
		change = new Change(10, 10, 10, 10);
		boolean checkChangeThresholds = change.checkChangeThresholds(10, 10, 11, 10);
		
		assertFalse(checkChangeThresholds);
	}
	
	@Test
	public void shouldCheckChangeThresholdsAreBelowSetLevelsForPound() {
		change = new Change(10, 10, 10, 10);
		boolean checkChangeThresholds = change.checkChangeThresholds(10, 10, 10, 11);
		
		assertFalse(checkChangeThresholds);
	}
}
