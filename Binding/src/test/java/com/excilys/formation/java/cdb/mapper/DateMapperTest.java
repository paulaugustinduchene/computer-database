package com.excilys.formation.java.cdb.mapper;

import static org.junit.Assert.*;

import java.sql.Date;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

public class DateMapperTest {


	private Date sqlDate = new Date(2020, 10, 12);
	private String stringDate = "2020-10-12";
	private LocalDate locDate = LocalDate.of(2020, 10, 12);
	 
	
	
	@Test
	public void testSqlToLocalDate() {
	
	}


	@Test
	public void testStringToLocalDate() {
		//fail("Not yet implemented");
	}

	@Test
	public void testLocalDateTosqlDate() {
		//fail("Not yet implemented");
	}

}
