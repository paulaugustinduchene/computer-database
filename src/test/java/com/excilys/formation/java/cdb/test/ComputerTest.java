package com.excilys.formation.java.cdb.test;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

import com.excilys.formation.java.cdb.beans.Computer;
import com.excilys.formation.java.cdb.mapper.DateMapper;

public class ComputerTest {
	
	@Test
	public void testGetId() {
		LocalDate intro = LocalDate.of(2011, 8, 25);
		LocalDate disc = LocalDate.of(2020, 1, 8);
		Computer computer = new Computer(5, "name", intro , disc, 6);
		int id = 5; 
		assertEquals("l'id renvoyé n'est pas le bon", id, computer.getId());
	}

	
	@Test
	public void testGetName() {
		LocalDate intro = LocalDate.of(2011, 8, 25);
		LocalDate disc = LocalDate.of(2020, 1, 8);
		Computer computer = new Computer(5, "name", intro , disc, 6);
		String name = "name"; 
		assertEquals("", name, computer.getName());
	}

	
	@Test
	public void testGetIntroduced() {
		LocalDate intro = LocalDate.of(2011, 8, 25);
		LocalDate disc = LocalDate.of(2020, 1, 8);
		Computer computer = new Computer(5, "name", intro , disc, 6);
		LocalDate date = LocalDate.of(2011, 8, 25); 
		assertEquals("", date, computer.getIntroduced());
	}

	
	@Test
	public void testGetDiscontinued() {
		LocalDate intro = LocalDate.of(2011, 8, 25);
		LocalDate disc = LocalDate.of(2020, 1, 8);
		Computer computer = new Computer(5, "name", intro , disc, 6);
		LocalDate date = LocalDate.of(2020, 1, 8);
		assertEquals("", date, computer.getDiscontinuted());
	}
	
	
	@Test
	public void testGetCompany_id(){
		LocalDate intro = LocalDate.of(2011, 8, 25);
		LocalDate disc = LocalDate.of(2020, 1, 8);
		Computer computer = new Computer(5, "name", intro , disc, 6);
		int company_id = 6;
		assertEquals("", company_id, computer.getCompany_id());
	}
	
	
	@Test
	public void testSetId(){
		int id = 6;
		Computer computer = new Computer();
		computer.setId(id);
		assertTrue("", computer.getId() == id);
	}
	
	
	@Test
	public void testSetName(){
		
		String string = "name";
		Computer computer = new Computer();
		computer.setName("name");
		assertEquals("", string ,computer.getName() );
		

	}
	
	
}
