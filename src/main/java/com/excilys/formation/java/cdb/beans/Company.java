package com.excilys.formation.java.cdb.beans;

import javax.validation.constraints.*;

public class Company {
	
	@NotNull
	private int id;
	private String name;
	
	
	public Company(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public Company() {
		// TODO Auto-generated constructor stub
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

	
	public static class Builder {
		
		private int id;
		private String name;
		

		public Builder setId(int id) {
			this.id = id;
			return this;
		}

		public Builder setName(String name) {
			this.name = name;
			return this; 
		}
		
		public Company build() {
			Company company = new Company();
			company.id = this.id;
			company.name = this.name;
			return company;
	
		}
		
	}
	
}

