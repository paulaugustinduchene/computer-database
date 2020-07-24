package com.excilys.formation.java.cdb.mapper;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import com.excilys.formation.java.cdb.beans.Computer;

public class ComputerMapper {
	
	public static Computer getComputer(ResultSet results) throws SQLException {
		
	
		
		return new Computer.Builder().setIdComputer(results.getInt("id")).setName(results.getString("name"))
				.setIntroduced(DateMapper.sqlToLocalDate(results.getDate("introduced"))).
				setDiscontinued(DateMapper.sqlToLocalDate(results.getDate("discontinued")))
				.setCompany_id(results.getInt("company_id")).build();
	
	}
	
}