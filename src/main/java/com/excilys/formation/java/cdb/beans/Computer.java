package com.excilys.formation.java.cdb.beans;
import java.sql.Date;
import java.time.LocalDate;

public class Computer {
	
	private int id;
	private String name;
	private LocalDate introduced;
	private LocalDate discontinuted;
	private int company_id;
	
	
	
	public Computer(int id, String name, LocalDate introduced, LocalDate discontinuted, int company_id) {
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinuted = discontinuted;
		this.company_id = company_id;
	}

	public Computer() {
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
	public LocalDate getIntroduced() {
		return introduced;
	}
	public void setIntroduced(LocalDate introduced) {
		this.introduced = introduced;
	}
	public LocalDate getDiscontinuted() {
		return discontinuted;
	}
	public void setDiscontinuted(LocalDate localDate) {
		this.discontinuted = localDate;
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

	
	
	 public static class Builder {
		 
		  	private int idComputer;
	        private String name;
	        private LocalDate introducedDate;
	        private LocalDate discontinuedDate;
	        private int company;

	        public Builder setIdComputer(int idComputer) {
	            this.idComputer = idComputer;
	            return this;
	        }

	        public Builder setName(String name) {
	            this.name = name;
	            return this;
	        }

	        public Builder setIntroduced(LocalDate introducedDate) {
	            this.introducedDate = introducedDate;
	            return this;
	        }

	        public Builder setDiscontinued(LocalDate discontinuedDate) {
	            this.discontinuedDate = discontinuedDate;
	            return this;
	        }

	        public Builder setCompany(int  company) {
	            this.company = company;
	            return this;
	        }

	        public Computer build() {
	            Computer computer = new Computer();
	            computer.id = this.idComputer;
	            computer.name = this.name;
	            computer.introduced = this.introducedDate;
	            computer.discontinuted = this.discontinuedDate;
	            computer.company_id = this.company;
	            return computer;
	        }
	    }
		 
	 
	
}


