package com.meritamerica.assignment3;

import java.util.Date;

import java.lang.NumberFormatException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class BankAccount {
	protected long accountNumber;
	protected double balance;
	protected double interestRate;
	protected Date accountOpenedOn; 
	
	public BankAccount() {
		
	}
	
	public BankAccount(double openingBalance) {
		this.balance = openingBalance;
	}
	
	public BankAccount(double balance, double interestRate) {
		this.balance = balance;
		this.interestRate = interestRate;
		MeritBank.getNextAccountNumber();
	}
	
	public BankAccount(double balance, double interestRate, Date accountOpenedOn) {
		this.balance = balance;
		this.interestRate = interestRate;
		MeritBank.getNextAccountNumber();
		this.accountOpenedOn = accountOpenedOn;
	}
	
	public BankAccount(long accountNumber, double balance, double interestRate) {
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.interestRate = interestRate;
	}
	
	public BankAccount(long accountNumber, double balance, double interestRate, Date accountOpenedOn) {
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.interestRate = interestRate;
		this.accountOpenedOn = accountOpenedOn;
	}
	
	public long getAccountNumber() {
		return accountNumber;
	}
	
	public void setAccountNumber(long accountNum) {
		accountNumber = accountNum;
	}

	public double getBalance() {
		return balance;
	}
	
	public void setBalance(double balance) {
		this.balance = balance;
	}

	public double getInterestRate() {
		return interestRate;
	}
	
	public void setInterestRate(double intRate) {
		interestRate = intRate;
	}
	
	public Date getOpenedOn() {
		return accountOpenedOn;
	}
	
	public void setAccountOpenedOn(Date date) {
		accountOpenedOn = date;
	}
	
	public boolean withdraw(double amount) {
		if (amount <= balance && amount > 0) {
			balance -= amount;
			return true;
		} else {
			return false;
		}
	}
	
	public boolean deposit(double amount) {
		if (amount > 0) {
			balance += amount;
			return true;
		} else return false;
	}
	
	public double futureValue(double years) {
		double futureValue = (getBalance() * Math.pow((1+interestRate), years));
		return futureValue;
	}
	
	static BankAccount readFromString(String accountData) throws ParseException{
		BankAccount fromStringAccount = new BankAccount();
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
		String[] accountDataFormatter = accountData.split(",");
		fromStringAccount.setAccountNumber(Long.parseLong(accountDataFormatter[0]));
		fromStringAccount.setBalance(Double.parseDouble(accountDataFormatter[1]));
		fromStringAccount.setInterestRate(Double.parseDouble(accountDataFormatter[2]));
		fromStringAccount.accountOpenedOn = dateFormatter.parse(accountDataFormatter[3]);	
		return fromStringAccount;
	}

	@Override
	public String toString() {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
		String dateString = dateFormatter.format(accountOpenedOn);
		return accountNumber + "," + balance + "," + interestRate
				+ "," + dateString;
	}
	
}