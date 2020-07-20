package com.excilys.formation.java.cdb.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.formation.java.cdb.beans.Computer;
import com.excilys.formation.java.cdb.dao.ComputerDao;
import com.excilys.formation.java.cdb.dao.ComputerDaoImpl;
import com.excilys.formation.java.cdb.dao.DaoConnexion;


@Service
public class ComputerServices {

	@Autowired
	private ComputerDaoImpl computerDao;
	private static Logger logger = LoggerFactory.getLogger(ComputerServices.class);

	public ComputerServices() {

	}

	public List<Computer> afficherliste() {
		List<Computer> computers = computerDao.list();
		return computers;
	}

	public List<Computer> afficherPage(int low, int high) {
		List<Computer> computers = computerDao.listpage(low, high);
		return computers;
	}

	public Computer showDetails(int id) {
		Computer computer = computerDao.list().get(id);
		return computer;
	}

	public void create(Computer computer) {
		computerDao.add(computer);
		logger.info("name in Dao Impl: " + computer.getName());
	}

	public void delete(Computer computer) {
		computerDao.delete(computer);
	}

	public void update(Computer computer) {
		
		logger.info("name : " + computer.getName());
		
		try {
			computerDao.update(computer);
			logger.info("update oK");
		} catch (Exception e) {
			logger.error("update impossible");
		}
	}

	
	public List<Computer> search(StringBuilder search) {
		List<Computer> computers = new ArrayList<Computer>();
		try {
			computers = computerDao.getByName(search.toString());
		} catch (Exception e) {
			logger.error("ERROR in computerservices .search()");
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return computers;
	}
	
	
	
	public List<Computer> orderByComputer() {
		List<Computer> computers = new ArrayList<Computer>();
		try {
			computers = computerDao.orderByComputer();
		} catch (Exception e) {
			logger.error("ERROR in computerservices .orderByComputer()");
		}
		return computers;
	}
	
	
	
	public int countComputers() {
		int computerNb = computerDao.countComputer();
		return computerNb;
	}
	
	
	
	
	
}