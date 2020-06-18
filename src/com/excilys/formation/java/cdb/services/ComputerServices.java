package com.excilys.formation.java.cdb.services;

import java.util.List;

import com.excilys.formation.java.cdb.beans.Computer;
import com.excilys.formation.java.cdb.dao.ComputerDao;
import com.excilys.formation.java.cdb.dao.ComputerDaoImpl;
import com.excilys.formation.java.cdb.dao.DaoConnexion;

public class ComputerServices {
	
	private static DaoConnexion daoconnexion = new DaoConnexion();
	
	public ComputerServices() {
		
	}

	public static void afficherliste() {
		
		List<Computer> computers = daoconnexion.getComputerDao().list();
		System.out.println(computers);
	}

}
