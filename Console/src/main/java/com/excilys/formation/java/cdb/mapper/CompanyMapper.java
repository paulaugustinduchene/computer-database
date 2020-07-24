package com.excilys.formation.java.cdb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.excilys.formation.java.cdb.beans.Company;
import com.excilys.formation.java.cdb.beans.Computer;

public class CompanyMapper {

public static Company getCompany(ResultSet results) throws SQLException {
		
		return new Company.Builder().setId(results.getInt("id"))
				.setName(results.getString("name")).build();
	}
	
}
