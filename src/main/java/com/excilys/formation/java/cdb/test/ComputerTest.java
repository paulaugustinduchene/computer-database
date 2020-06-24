package com.excilys.formation.java.cdb.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.excilys.formation.java.cdb.beans.Computer;

public class ComputerTest {

	public void testGetId() {
		Computer computer = new Computer(5, "name", null, null, 6);
		int id = 5; 
		assertEquals("l'id renvoyé n'est pas le bon", computer.getId(), id);
	}

	public void testGetName() {
		Computer computer = new Computer(5, "name", null, null, 6);
		String name = "name"; 
		assertEquals("", computer.getName(), name);
	}

	public void testGetIntroduced() {

	}

	public void testGetDiscontinued() {

	}
	
	public void testGetCompany_id() {
		
	}
}
