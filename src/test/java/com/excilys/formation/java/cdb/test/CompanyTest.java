package com.excilys.formation.java.cdb.test;


import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.excilys.formation.java.cdb.beans.Company;

public class CompanyTest {

	@Test    
	public void testGetId(){
		Company computer = new Company(5, "name");
		int id = 5; 
		assertEquals("l'id renvoyé n'est pas le bon", computer.getId(), id);
	}
	
	@Test
	public void testGetName(){
		Company computer = new Company(5, "name");
		int id = 5; 
		assertEquals("l'id renvoyé n'est pas le bon", computer.getId(), id);
	}
	

}
