package com.excilys.formation.java.cdb.beans;
import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.*;

import org.hibernate.annotations.GenericGenerator;

import com.excilys.formation.java.cdb.services.CompanyServices;

@Entity
@Table(name="computer")
public class Computer {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	@NotNull
	private Integer id;
	
	@NotNull
	@Size(max=255)
	private String name;
	
	private LocalDate introduced;
	
	private LocalDate discontinued;
	

	private Integer company_id; 
	
//	@ManyToOne
//	private Company company; 
	
	
	public Computer(Integer id, String name, LocalDate introduced, LocalDate discontinued, Integer company_id) {
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.company_id = company_id;
		//this.company = CompanyServices.afficherCompany(company_id);
	}

	public Computer() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
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
	public LocalDate getDiscontinued() {
		return discontinued;
	}
	public void setDiscontinued(LocalDate localDate) {
		this.discontinued = localDate;
	}
	
	public Integer getCompany_id() {
		return company_id;
		//return company.getId();
	}
	
	public void setCompany_id(Integer company_id) {
		this.company_id = company_id;
	}
	
//	public void setCompany_id(int company_id) {
//		this.company= CompanyServices.afficherCompany(company_id);
//	}
//	
//
//	public Company getCompany() {
//		return company;
//	}
//
//	public void setCompany(Company company) {
//		this.company = company;
//	}

	
	
	
	@Override
	public String toString() {
		return "Computer [id=" + id + ", name=" + name + ", introduced=" + introduced + ", discontinuted="
				+ discontinued + ", company_id=" +  company_id /* company.getName() */ + "]\n";
	}

	 public static class Builder {
		 
		  	private Integer idComputer;
	        private String name;
	        private LocalDate introducedDate;
	        private LocalDate discontinuedDate;
	        private Integer company_id;
//	        private Company company;

	        public Builder setIdComputer(Integer idComputer) {
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

	        
	        public Builder setCompany_id(Integer company_id) {
	            this.company_id = company_id;
	            return this;
	        }
	        
//	        public Builder setCompany(int  company_id) {
//	            this.company = CompanyServices.afficherCompany(company_id);
//	            return this;
//	        }

	        public Computer build() {
	            Computer computer = new Computer();
	            computer.id = this.idComputer;
	            computer.name = this.name;
	            computer.introduced = this.introducedDate;
	            computer.discontinued = this.discontinuedDate;
	            computer.company_id = this.company_id;
//	            computer.company = this.company;
	            return computer;
	        }
	    }
		 
	 
	
}


