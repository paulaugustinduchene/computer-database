package com.excilys.formation.java.cdb.mapper;

import java.time.LocalDate;

import com.excilys.formation.java.cdb.beans.Computer;
import com.excilys.formation.java.cdb.dto.ComputerDTO;

public class ComputerMapperDTO {
	
	public static ComputerDTO computerToDTO(Computer computer) {
		String name = computer.getName();
		String introduced = computer.getIntroduced().toString();
		String discontinued = computer.getIntroduced().toString();
		String company_id = String.valueOf(computer.getCompany_id());
		
		return new ComputerDTO(name, introduced,discontinued, company_id);
	}
	
	public static Computer dtoToComputer(ComputerDTO computerDto){
		
		Integer id = null;
		
		if(computerDto.getId() != null && computerDto.getId() != "" ) {
			id = (int) Integer.valueOf(computerDto.getId());
		}
		
		String name = computerDto.getName();
		LocalDate introduced = DateMapper.stringToLocalDate(computerDto.getIntroduced());
		LocalDate discontinued = DateMapper.stringToLocalDate(computerDto.getIntroduced());
		
		if(computerDto.getCompanyId() != null){
		int company_id = (int) Integer.valueOf(computerDto.getCompanyId());

		Computer computer = new Computer.Builder().setIdComputer(id).setName(name).setIntroduced(introduced)
				.setDiscontinued(discontinued).setCompany_id(company_id).build();
		
		return computer;
		
		}else {
			Computer computer = new Computer.Builder().setIdComputer(id).setName(name).setIntroduced(introduced)
					.setDiscontinued(discontinued).build();
			return computer;
		}
	}
}
