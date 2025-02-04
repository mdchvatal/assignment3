package com.meritamerica.assignment3;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.meritamerica.assignment3.CDAccount;
import com.meritamerica.assignment3.CDOffering;
import com.meritamerica.assignment3.CheckingAccount;
import com.meritamerica.assignment3.SavingsAccount;

public class AccountHolder implements Comparable<AccountHolder>{

	private double checkingBalance;
	private double savingsBalance;
	private double CDBalance;
	private double combinedBalance;
	private CDAccount[] cdAccounts = new CDAccount[10];
	private SavingsAccount[] savingsAccounts = new SavingsAccount[10];
	private CheckingAccount[] checkingAccounts = new CheckingAccount[10];
	private String firstName;
	private String middleName;
	private String lastName;
	private String ssn;
	private int numberOfCheckingAccounts = 0;
	private int numberOfSavingsAccounts = 0;
	private int numberOfCDAccounts = 0;
	private long accountNumber;
	
	public AccountHolder() {
		
	}
	
	public AccountHolder(String firstName, String middleName, String lastName, String ssn) {
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.ssn = ssn;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getMiddleName() {
		return middleName;
	}
	
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getSSN() {
		return ssn;
	}
	
	public void setSSN(String ssn) {
		this.ssn = ssn;
	}
	
	public CheckingAccount addCheckingAccount(double openingBalance) {
		CheckingAccount account = new CheckingAccount();
		account.setBalance(openingBalance);
		if (((this.getCheckingBalance() + this.getSavingsBalance()) + openingBalance < 250000)) {
			checkingAccounts[numberOfCheckingAccounts++] = account;
			return account;
		} else {
			return account;
		}
	}
	
	public CheckingAccount addCheckingAccount(CheckingAccount checkingAccount) {
		if ((this.getCheckingBalance() + this.getSavingsBalance()) + checkingAccount.getBalance() < 250000) {
			checkingAccounts[numberOfCheckingAccounts++] = checkingAccount;
			return checkingAccount;
		} else {
			return checkingAccount;
		}
	}
		
	public CheckingAccount[] getCheckingAccounts() {
		return checkingAccounts;
	}
		
	public int getNumberOfCheckingAccounts() {
		return numberOfCheckingAccounts;
	}
		
	public double getCheckingBalance() {
		double tempBalance = 0;
		for (int i = 0; i < (numberOfCheckingAccounts); i++) {
			tempBalance += checkingAccounts[i].getBalance();
		}
		//checkingBalance;
		return tempBalance;
	}
		
	public SavingsAccount addSavingsAccount(double openingBalance) {
		SavingsAccount account = new SavingsAccount();
		account.setBalance(openingBalance);
		if (((this.getCheckingBalance() + this.getSavingsBalance()) + openingBalance <= 250000)) {
			savingsAccounts[numberOfSavingsAccounts++] = account;
			return account;
		} else {
			return  account;
		}
	}
	
	public SavingsAccount addSavingsAccount(SavingsAccount savingsAccount) {
		if ((this.getCheckingBalance() + this.getSavingsBalance()) + savingsAccount.getBalance() < 250000) {
			savingsAccounts[numberOfSavingsAccounts++] = savingsAccount;
			return savingsAccount;
		} else {
			return savingsAccount;
		}
	}
	
	public SavingsAccount[] getSavingsAccounts() {
		return savingsAccounts;
	}
	
	public int getNumberOfSavingsAccounts() {
		return numberOfSavingsAccounts;
	}
		
	public double getSavingsBalance() {
		double tempBalance = 0;
		for (int i = 0; i < (numberOfSavingsAccounts); i++) {
			tempBalance += savingsAccounts[i].getBalance();
		}
		//savingsBalance = tempBalance;
		return tempBalance;
	}
		
	public CDAccount addCDAccount(CDOffering offering, double openingBalance) {
		cdAccounts[numberOfCDAccounts] = new CDAccount(offering, openingBalance);
		return cdAccounts[numberOfCDAccounts++];
	}
		
	public CDAccount addCDAccount(CDAccount cdAccount) {
		cdAccounts[numberOfCDAccounts] = cdAccount;
		return cdAccounts[numberOfCDAccounts++];
	}
		
	public CDAccount[] getCDAccounts() {
		return cdAccounts;
	}
		
	public int getNumberOfCDAccounts() {
		return numberOfCDAccounts;
	}
		
	public double getCDBalance() {
		double tempBalance = 0;
		for (int i = 0; i < (numberOfCDAccounts); i++) {
			tempBalance += cdAccounts[i].getBalance();
		}
		//CDBalance = tempBalance;
		return tempBalance;
	}
		
	public double getCombinedBalance() {
		combinedBalance = getCheckingBalance() + getSavingsBalance() + getCDBalance();
		return combinedBalance;
	}

	@Override
	public int compareTo(AccountHolder accHold) {
		if (this.getCombinedBalance() > accHold.getCombinedBalance()) {
			return 1;
		} else if (this.getCombinedBalance() < accHold.getCombinedBalance()) {
			return -1;
		} else {
		return 0;
		}
	}
	
	static AccountHolder readFromString(String accountHolderData) throws ParseException{
		AccountHolder fromStringAccount = new AccountHolder();
		try {
			String[] accountDataFormatter = accountHolderData.split(",", -1);
				fromStringAccount.lastName = accountDataFormatter[0];
				fromStringAccount.middleName = accountDataFormatter[1];
				fromStringAccount.firstName = accountDataFormatter[2];
				fromStringAccount.ssn = accountDataFormatter[3];	
		} catch (NumberFormatException e) {
			System.out.println("That's not valid data input");
		}
		return fromStringAccount;
	}

	@Override
	public String toString() {
		return lastName + "," + middleName + "," + firstName + "," + ssn;
	}
	
}