package com.excilys.formation.java.cdb.beans;

public class Page {
	
	private int low;
	private int high; 
	private int compPerPg;
	private int number;
	
	
	
	public Page(int low, int high, int compPerPg, int number) {
		super();
		this.low = low;
		this.high = high;
		this.compPerPg = compPerPg;
		this.number = number;
	}



	public int getLow() {
		return low;
	}



	public void setLow(int low) {
		this.low = low;
	}



	public int getHigh() {
		return high;
	}



	public void setHigh(int high) {
		this.high = high;
	}



	public int getCompPerPg() {
		return compPerPg;
	}



	public void setCompPerPg(int compPerPg) {
		this.compPerPg = compPerPg;
	}



	public int getNumber() {
		return number;
	}



	public void setNumber(int number) {
		this.number = number;
	} 
	
	

}
