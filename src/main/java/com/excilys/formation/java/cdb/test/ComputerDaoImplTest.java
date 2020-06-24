package com.excilys.formation.java.cdb.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.excilys.formation.java.cdb.dao.DaoConnexion;



public class ComputerDaoImplTest {
	


	@Test
	public void testListPage() {
		
		DaoConnexion daoconnexion = DaoConnexion.getInstance();
		int listsize =10;
		assertEquals(listsize,daoconnexion.getComputerDao().listpage(1, 10).size());
		
	}

}
