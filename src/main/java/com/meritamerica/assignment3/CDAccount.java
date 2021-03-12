package com.meritamerica.assignment3;

import java.util.Date;
import java.lang.NumberFormatException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CDAccount extends BankAccount {
	
		private long accountNumber;
		private double balance;
		private double interestRate;
		private Date accountOpenedOn; 
		private int term;
		
		public CDAccount() {
			
		}
		
		public CDAccount(double startingBalance) {
			this.balance = startingBalance;
		}
		
		public CDAccount(double balance, double interestRate) {
			this.balance = balance;
			this.interestRate = interestRate;
			MeritBank.getNextAccountNumber();
		}
		
		public CDAccount(double balance, double interestRate, Date accountOpenedOn) {
			this.balance = balance;
			this.interestRate = interestRate;
			MeritBank.getNextAccountNumber();
			this.accountOpenedOn = accountOpenedOn;
		}
		
		public CDAccount(long accountNumber, double balance, double interestRate) {
			this.accountNumber = accountNumber;
			this.balance = balance;
			this.interestRate = interestRate;
		}
		
		public CDAccount(long accountNumber, double balance, double interestRate, Date accountOpenedOn) {
			this.accountNumber = accountNumber;
			this.balance = balance;
			this.interestRate = interestRate;
			this.accountOpenedOn = accountOpenedOn;
		}
		
		public long getAccountNumber() {
			return accountNumber;
		}

		public double getBalance() {
			return balance;
		}

		public double getInterestRate() {
			return interestRate;
		}
		
		public Date getOpenedOn() {
			return accountOpenedOn;
		}
		
		public int getTerm() {
			return term;
		}
		
		public boolean withdraw(double amount) {
			return false;
		}
		
		boolean deposit(double amount) {
			return false;
		}
		
		double futureValue(double years) {
			double futureValue = (balance * Math.pow((1+interestRate), years));
			return futureValue;
		}
		
		static CDAccount readFromString(String accountData) throws ParseException {
			CDAccount fromStringAccount = new CDAccount();
			try {
				SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
				String[] accountDataFormatter = accountData.split(",");
				fromStringAccount.accountNumber = Long.parseLong(accountDataFormatter[0]);
				fromStringAccount.balance = Integer.parseInt(accountDataFormatter[1]);
				fromStringAccount.interestRate = Integer.parseInt(accountDataFormatter[2]);
				fromStringAccount.accountOpenedOn = dateFormatter.parse(accountDataFormatter[3]);
				fromStringAccount.term = Integer.parseInt(accountDataFormatter[4]);
			} catch (NumberFormatException e) {
				System.out.println("That's not valid data input");
			}
			return fromStringAccount;
		}

		@Override
		public String toString() {
			SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
			String dateString = dateFormatter.format(accountOpenedOn);
			return accountNumber + "," + balance + "," + interestRate
					+ "," + dateString + "," + term;
		}
		
	}
}