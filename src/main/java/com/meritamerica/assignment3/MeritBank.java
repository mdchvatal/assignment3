package com.meritamerica.assignment3;

import java.util.Arrays;
import java.util.Collections;

public class MeritBank {
	private static AccountHolder[] accountHolders = new AccountHolder[20];
	private static int ahArrayCounter = 0;
	private static CDOffering[] cdOfferings = {new CDOffering(1, 0.018), new CDOffering(2, 0.019), new CDOffering(3, 0.02), new CDOffering(4, 0.025), new CDOffering(10, 0.022)};
	private static double totalBalances;
	private static int numberOfCDOfferings = 5;
	private static long accountNumber = 1000;
	
	
	public static void addAccountHolder(AccountHolder accountHolder) {
		accountHolders[ahArrayCounter] = accountHolder;
		ahArrayCounter++;
	}
	
	public static AccountHolder[] getAccountHolders() {
		return accountHolders;
	}

	public static CDOffering[] getCDOfferings() {
			return cdOfferings;
		
	}
	
	public static CDOffering getBestCDOffering(double depositAmount) {
		Collections.sort(Arrays.asList(cdOfferings));
		return cdOfferings[0];
	}
	
	public static CDOffering getSecondBestCDOffering(double depositAmount) {
		Collections.sort(Arrays.asList(cdOfferings));
		return cdOfferings[1];
	}
	
	public static void clearCDOfferings() {
		cdOfferings = null;
		numberOfCDOfferings = 0;
	}
	
	public static void setCDOfferings(CDOffering[] offerings) {
		cdOfferings = new CDOffering[offerings.length];
		for (int i = 0; i < offerings.length; i++) {
			cdOfferings[i] = offerings[i];
		}	 	
	}
	
	public static long getNextAccountNumber() {
		accountNumber++;
		return accountNumber;
	}
	
	public static double getTotalBalances(AccountHolder[] holders) {
		MeritBank.totalBalances = 0;
		for (int i = 0; i < ahArrayCounter; i++) {
			MeritBank.totalBalances += MeritBank.accountHolders[i].getCombinedBalance();
		}
		return MeritBank.totalBalances;
	}
	
	public static double futureValue(double presentValue, double interestRate, int term) {
		double total = (presentValue * Math.pow((1+interestRate), term));
		return total;
	}

}