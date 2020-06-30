package com.excilys.formation.java.cdb.mapper;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import com.excilys.formation.java.cdb.beans.Computer;

public class ComputerMapper {
	
	public static Computer getComputer(ResultSet results) throws SQLException {
		
		return new Computer(results.getInt("id"),
				results.getString("name"), 
				DateMapper.sqlToLocalDate(results.getDate("introduced")),
				DateMapper.sqlToLocalDate(results.getDate("discontinued")),
				results.getInt("company_id"));
	}
	
	
	
	
	
	
	
	
	

}