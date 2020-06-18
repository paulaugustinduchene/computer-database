package com.excilys.formation.java.cdb.test;

import java.util.List;

import com.excilys.formation.java.cdb.beans.Computer;
import com.excilys.formation.java.cdb.dao.CompanyDao;
import com.excilys.formation.java.cdb.dao.CompanyDaoImpl;
import com.excilys.formation.java.cdb.dao.ComputerDao;
import com.excilys.formation.java.cdb.dao.ComputerDaoImpl;
import com.excilys.formation.java.cdb.dao.DaoConnexion;

public class TestDao {

	public static void main(String[] args) {
		
		DaoConnexion daoConnexion = new DaoConnexion();
		
		ComputerDao computerDao = new ComputerDaoImpl(daoConnexion); 
		
		System.out.println(computerDao.list().get(0).toString());
		System.out.println(computerDao.list().get(28).toString());
		System.out.println(computerDao.list().get(5).toString());
		System.out.println(computerDao.list().get(36).toString());
		
		Computer computer1 = new Computer(575,"Acer Helios 300 Predator", null, null, 6);
		Computer computer2 = new Computer(575,"Acer Helios 500 Predator", null, null, 6);
		
		computerDao.add(computer1);
		
		System.out.println(computerDao.list().toString());
		
		System.out.println("\n Nouvelle liste \n");
		
		
		computerDao.update(computer2);
		
		System.out.println("\n Nouvelle liste delete \n");
		System.out.println(computerDao.list().toString());
		
		
		computerDao.delete(computer2);
		
		System.out.println(computerDao.list().toString());
		


	}

}
;