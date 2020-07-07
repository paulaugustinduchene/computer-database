package com.excilys.formation.java.cdb.mapper;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateMapper {

	public static final DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	public static LocalDate sqlToLocalDate(Date date) {
		
		LocalDate locdate;
		
		if(date == null) {
			locdate = null; 
		}
		
		else {
			locdate = LocalDate.parse(date.toString(),format);
		}
		return locdate;	
	}
	
	
	public static LocalDate stringToLocalDate(String date) {
		LocalDate locDate;
		if(date == null) {
			locDate = null;
		}else if(date.equals("null")) {
			locDate = null;
		}else {
			locDate = LocalDate.parse(date,format);
		}
		return locDate;
	}
	
	
	public static Date localDateTosqlDate(LocalDate date) {
		Date locDate;
		if(date == null) {
			locDate = null;
		}else{
			locDate = Date.valueOf(date.toString());
		}
		
		return locDate;
	}
	
	
}

