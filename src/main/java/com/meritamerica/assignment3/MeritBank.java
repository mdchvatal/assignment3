package com.meritamerica.assignment3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MeritBank {
	private static ArrayList<String> fileStringArrayList = new ArrayList<String>();
	private static AccountHolder[] accountHolders = new AccountHolder[20];
	private static int ahArrayCounter = 0;
	private static CDOffering[] cdOfferings;
	private static double totalBalances;
	private static int numberOfCDOfferings;
	private static long accountNumber;
	private static int numberOfAccountHolders;
	
	
	static boolean readFromFile(String fileName) {
		fileStringArrayList = new ArrayList<String>();
		String line;
		try (FileReader fileReader = new FileReader(fileName); 
				BufferedReader buffRead = new BufferedReader(fileReader)) {
					while ((line = buffRead.readLine()) != null) {
						fileStringArrayList.add(line);
						//System.out.println(line);
					}
					doStuffWithFile();
		return true;				
		} catch (IOException ioe) {
			System.out.println("Bad file name. Try Again");
			return false;
		}catch (NumberFormatException nfe) {
			return false;
		}
	} 
	
	static boolean writeToFile(String fileName) {
		try (FileWriter fileWriter = new FileWriter(fileName);
				PrintWriter printWriter = new PrintWriter(fileWriter)) {
			fileStringArrayList.forEach((listString) -> printWriter.println(listString));
			return true;
		} catch (IOException ioe) {System.out.println("Error");}
		return false;	
	}
	
	public static void doStuffWithFile() {
		int lineCounter = 0;
		setNextAccountNumber(Long.parseLong(fileStringArrayList.get(lineCounter)));
		++lineCounter;
		numberOfCDOfferings = Integer.parseInt(fileStringArrayList.get(lineCounter));
		++lineCounter;
		System.out.println("Number of CDOffs" + numberOfCDOfferings);
		if (numberOfCDOfferings > 0) {
			cdOfferings = new CDOffering[numberOfCDOfferings];
			for (int i = 0; i < numberOfCDOfferings; i++) {
				cdOfferings[i] = CDOffering.readFromString(fileStringArrayList.get(lineCounter));
				lineCounter++;
			}				
		}
		numberOfAccountHolders = Integer.parseInt(fileStringArrayList.get(lineCounter));
		lineCounter++;
		System.out.println("number of acc holders " + numberOfAccountHolders);
		if (numberOfAccountHolders > 0) {
			for (int i = 0; i < numberOfAccountHolders; i++)
			try {
				
				MeritBank.addAccountHolder(AccountHolder.readFromString(fileStringArrayList.get(lineCounter)));
				lineCounter++;
				int numberOfCheckingAccounts = Integer.parseInt(fileStringArrayList.get(lineCounter));
				System.out.println("Number of checking account " + numberOfCheckingAccounts);
				if (numberOfCheckingAccounts > 0) {
					//lineCounter++;
					for (int j = 0; j < numberOfCheckingAccounts; j++) {
						lineCounter++;
						MeritBank.accountHolders[i].addCheckingAccount(CheckingAccount.readFromString(fileStringArrayList.get(lineCounter)));
					}
					lineCounter++;
				} else {lineCounter++;}
				int numberOfSavingsAccounts = Integer.parseInt(fileStringArrayList.get(lineCounter));
				if (numberOfSavingsAccounts > 0) {
					for (int k = 0; k < numberOfSavingsAccounts; k++) {
						lineCounter++;
						MeritBank.accountHolders[i].addSavingsAccount(SavingsAccount.readFromString(fileStringArrayList.get(lineCounter)));
						
					}
					lineCounter++;
				} else {lineCounter++;}
				int numberOfCDAccounts = Integer.parseInt(fileStringArrayList.get(lineCounter));
				if (numberOfCDAccounts > 0) {
					
					for (int l = 0; l < numberOfCDAccounts; l++) {
						lineCounter++;
						MeritBank.accountHolders[i].addCDAccount(CDAccount.readFromString(fileStringArrayList.get(lineCounter)));
						
					}
					lineCounter++;
				} else {lineCounter++;}
			} catch (ParseException pe) {
				pe.printStackTrace();
			}
		}
	}
	
	public static void addAccountHolder(AccountHolder accountHolder) {
		accountHolders[ahArrayCounter] = accountHolder;
		ahArrayCounter++;
	}
	
	public static AccountHolder[] getAccountHolders() {
		return accountHolders;
	}
	
	public static AccountHolder[] sortAccountHolders() {
	AccountHolder[] tempArray = new AccountHolder[numberOfAccountHolders];
		for (int i= 0; i < tempArray.length; i++) {
			tempArray[i] = accountHolders[i];
		}
		Arrays.sort(tempArray);
		return tempArray;
		
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
		return accountNumber++;
	}
	
	public static void setNextAccountNumber(long nextAccountNumber) {
		accountNumber = nextAccountNumber;
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