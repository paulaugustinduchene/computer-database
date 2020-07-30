package com.excilys.formation.java.cdb.dto;


public class ComputerDTO {

	private String id;
	private String name;
	private String introduced;
	private String discontinued;
	private String companyId;

	public ComputerDTO() {
	}

	public ComputerDTO(String name, String introduced, String discontinued, String companyId) {
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.companyId = companyId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIntroduced() {
		return introduced;
	}

	public void setIntroduced(String introduced) {
		this.introduced = introduced;
	}

	public String getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(String discontinued) {
		this.discontinued = discontinued;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public static class Builder{

		private String id;
		private String name;
		private String introduced;
		private String discontinued;
		private String companyId;

		public Builder setId(String id) {
			this.id = id;
			return this;
		}

		public Builder setName(String name) {
			this.name = name;
			return this;
		}

		public Builder setIntroduced(String introduced) {
			this.introduced = introduced;
			return this;
		}

		public Builder setDiscontinued(String discontinued) {
			this.discontinued = discontinued;
			return this;
		}

		public Builder setCompanyId(String companyId) {
			this.companyId = companyId;
			return this;
		}

		public ComputerDTO build() {
			ComputerDTO computer = new ComputerDTO();
			computer.id = this.id;
			computer.name = this.name;
			computer.introduced = this.introduced;
			computer.discontinued = this.discontinued;
			computer.companyId = this.companyId;
			return computer;


		}
	}
}
