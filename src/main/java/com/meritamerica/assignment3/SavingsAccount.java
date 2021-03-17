package com.meritamerica.assignment3;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SavingsAccount extends BankAccount {
	
	public SavingsAccount() {
		
	}
	public SavingsAccount(long accountNumber, double balance, double interestRate, Date accountOpenedOn) {
		super.accountNumber = accountNumber;
		super.balance = balance;
		super.interestRate = interestRate;
		super.accountOpenedOn = accountOpenedOn;
	}
	
	static SavingsAccount readFromString(String accountData) throws ParseException{
		SavingsAccount fromStringAccount = new SavingsAccount();
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
		String[] accountDataFormatter = accountData.split(",");
		fromStringAccount.accountNumber = Long.parseLong(accountDataFormatter[0]);
		fromStringAccount.balance = Double.parseDouble(accountDataFormatter[1]);
		fromStringAccount.interestRate = Double.parseDouble(accountDataFormatter[2]);
		fromStringAccount.accountOpenedOn = dateFormatter.parse(accountDataFormatter[3]);	
		return fromStringAccount;
	}
	
}

