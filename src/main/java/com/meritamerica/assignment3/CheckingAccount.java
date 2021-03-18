package com.meritamerica.assignment3;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CheckingAccount extends BankAccount{ 
	
	private double interestRate = 0.0001;
	
	
	public CheckingAccount() {
		
		
	}
	public double getBalance() {
		return balance;
	}
	
	@Override
	public double futureValue(double years) {
		double futureValue = (balance * Math.pow((1 + this.interestRate), years));
		return futureValue;
	}
	
	public CheckingAccount(long accountNumber, double balance, double interestRate, Date accountOpenedOn) {
		super.accountNumber = accountNumber;
		super.setBalance(balance);
		super.interestRate = interestRate;
		super.accountOpenedOn = accountOpenedOn;
	}
	
	static CheckingAccount readFromString(String accountData) throws ParseException{
		System.out.println(accountData);
		CheckingAccount fromStringAccount = new CheckingAccount();
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
		String[] accountDataFormatter = accountData.split(",");
		if (accountDataFormatter.length != 4) {
			throw new NumberFormatException();
			
		}else {
			fromStringAccount.setAccountNumber(Long.parseLong(accountDataFormatter[0]));
			fromStringAccount.setBalance(Double.parseDouble(accountDataFormatter[1]));
			fromStringAccount.setInterestRate(Double.parseDouble(accountDataFormatter[2]));
			fromStringAccount.setAccountOpenedOn(dateFormatter.parse(accountDataFormatter[3]));	
			return fromStringAccount;
		} 
	}
	
}