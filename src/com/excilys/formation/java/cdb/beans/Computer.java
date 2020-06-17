package com.excilys.formation.java.cdb.beans;

import java.util.Date;

public class Computer {
	
	private int id;
	private String name;
	private Date introduced;
	private Date discontinuted;
	private int company_id;
	
	
	
	public Computer(int id, String name, Date introduced, Date discontinuted, int company_id) {
		super();
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinuted = discontinuted;
		this.company_id = company_id;
	}
	public Computer() {
	
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getIntroduced() {
		return introduced;
	}
	public void setIntroduced(Date introduced) {
		this.introduced = introduced;
	}
	public Date getDiscontinuted() {
		return discontinuted;
	}
	public void setDiscontinuted(Date discontinuted) {
		this.discontinuted = discontinuted;
	}
	public int getCompany_id() {
		return company_id;
	}
	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
	
	@Override
	public String toString() {
		return "Computer [id=" + id + ", name=" + name + ", introduced=" + introduced + ", discontinuted="
				+ discontinuted + ", company_id=" + company_id + "]\n";
	}

	
	
	
	
}
