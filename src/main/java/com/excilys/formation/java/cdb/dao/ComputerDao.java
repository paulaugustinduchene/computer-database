package com.excilys.formation.java.cdb.dao;

import java.util.List;
import com.excilys.formation.java.cdb.beans.Computer;

public interface ComputerDao {
	
	public List<Computer> list();
		
	public void add(Computer computer);
	
	public void delete(Computer computer);
	
	public void update(Computer computer);

	public List<Computer> listpage(int low, int high);
	
	
}