package com.meritamerica.assignment3;


import java.lang.NumberFormatException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CDAccount extends BankAccount {
	private int term;

	static CDAccount readFromString(String accountData) throws ParseException {
			CDAccount fromStringAccount = new CDAccount();
			try {
				SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
				String[] accountDataFormatter = accountData.split(",");
				fromStringAccount.setAccountNumber(Long.parseLong(accountDataFormatter[0]));
				fromStringAccount.balance = Integer.parseInt(accountDataFormatter[1]);
				fromStringAccount.setInterestRate(Integer.parseInt(accountDataFormatter[2]));
				fromStringAccount.setAccountOpenedOn(dateFormatter.parse(accountDataFormatter[3]));
				fromStringAccount.term = Integer.parseInt(accountDataFormatter[4]);
			} catch (NumberFormatException e) {
				System.out.println("That's not valid data input");
			}
			return fromStringAccount;
		}

	@Override
	public String toString() {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
		String dateString = dateFormatter.format(getOpenedOn());
		return getAccountNumber() + "," + getBalance() + "," + getInterestRate()
					+ "," + dateString + "," + term;
	}
}