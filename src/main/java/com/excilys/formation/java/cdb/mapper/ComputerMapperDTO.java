package com.excilys.formation.java.cdb.mapper;

import java.time.LocalDate;

import com.excilys.formation.java.cdb.beans.Computer;
import com.excilys.formation.java.cdb.dto.ComputerDTO;

public class ComputerMapperDTO {
	
	public ComputerDTO computerToDTO(Computer computer) {
		String name = computer.getName();
		String introduced = computer.getIntroduced().toString();
		String discontinued = computer.getIntroduced().toString();
		String company_id = String.valueOf(computer.getCompany_id());
		
		return new ComputerDTO(name, introduced,discontinued, company_id);
	}
	
	public Computer dtoToComputer(ComputerDTO computerDto){
		int id = Integer.valueOf(computerDto.getId());
		String name = computerDto.getName();
		LocalDate introduced = DateMapper.stringToLocalDate(computerDto.getIntroduced());
		LocalDate discontinued = DateMapper.stringToLocalDate(computerDto.getIntroduced());
		int company_id = (int) Integer.valueOf(computerDto.getCompanyId());
		
		
		Computer computer = new Computer();
		computer.setId(id);
		computer.setName(name);
		computer.setIntroduced(introduced);
		computer.setDiscontinuted(discontinued);
		computer.setCompany_id(company_id);
	
		return computer;
		
	}
	

}
