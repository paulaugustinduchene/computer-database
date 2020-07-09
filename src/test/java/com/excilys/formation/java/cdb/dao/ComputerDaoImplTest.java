package com.excilys.formation.java.cdb.dao;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.stereotype.Component;

import com.excilys.formation.java.cdb.dao.DaoConnexion;


@Component
public class ComputerDaoImplTest {
	
	@Test
	public void testListPage() {
		
		DaoConnexion daoconnexion = DaoConnexion.getInstance();
		int listsize =10;
		assertEquals("list size ins't the one expected",listsize,daoconnexion.getComputerDao().listpage(1, 10).size());
	}

}
