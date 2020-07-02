package com.excilys.formation.java.cdb.services;

import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.formation.java.cdb.beans.Computer;
import com.excilys.formation.java.cdb.dao.ComputerDao;
import com.excilys.formation.java.cdb.dao.ComputerDaoImpl;
import com.excilys.formation.java.cdb.dao.DaoConnexion;
import com.excilys.formation.java.cdb.servlets.AddComputerServlet;

public class ComputerServices {
	
	private static DaoConnexion daoconnexion = DaoConnexion.getInstance();
	private static Logger logger = LoggerFactory.getLogger(ComputerServices.class);
	
	public ComputerServices() {
		
	}

	public static List<Computer> afficherliste() {
		List<Computer> computers = daoconnexion.getComputerDao().list();
		return computers;
	}
	
	public static List<Computer> afficherPage(int low, int high){
		List<Computer> computers = daoconnexion.getComputerDao().listpage(low,high);
		//System.out.println(computers);
		return computers; 
	}
	

	public static Computer showDetails(int id) {
		Computer computer = daoconnexion.getComputerDao().list().get(id);
		return computer;
	}

	public static void create(Computer computer) {
		daoconnexion.getComputerDao().add(computer);
	}

	public static void delete(Computer computer) {
		daoconnexion.getComputerDao().delete(computer);		
	}

	public static void update(Computer computer) {
		try {
		daoconnexion.getComputerDao().update(computer);
		logger.info("update oK");
		}catch(Exception e) {
			logger.error("update impossible");
		}
	}
}