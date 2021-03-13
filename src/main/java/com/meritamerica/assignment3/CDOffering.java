package com.meritamerica.assignment3;

public class CDOffering implements Comparable<CDOffering>{
	
	private int term;
	private double interestRate;
	
	public CDOffering(int term, double interestRate) {
		this.term = term;
		this.interestRate = interestRate;
	}

	public int getTerm() {
		return term;
	}

	public double getInterestRate() {
		return interestRate;
	}

	@Override
	public int compareTo(CDOffering o) {
		if ((Math.pow((1+this.getInterestRate()), this.getTerm())) > (Math.pow((1+o.getInterestRate()), o.getTerm()))) {
			return 1;
		} else if ((Math.pow((1+this.getInterestRate()), this.getTerm())) == (Math.pow((1+o.getInterestRate()), o.getTerm()))) {
			return 0;
		} else {
			return -1;
		}
	}

}
