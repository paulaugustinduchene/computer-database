package com.excilys.formation.java.cdb.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.formation.java.cdb.beans.Company;
import com.excilys.formation.java.cdb.dao.CompanyDao;
import com.excilys.formation.java.cdb.dao.CompanyDaoImpl;
import com.excilys.formation.java.cdb.dao.ComputerDaoImpl;
import com.excilys.formation.java.cdb.dao.DaoConnexion;

@Service
public class CompanyServices {
	
	@Autowired
	private CompanyDao companyDao;
	
	public CompanyServices() {
		
	}

	public  List<Company> afficherliste() {
		List<Company> companies = companyDao.list();
		return companies;
	}
	
	public void delete(int company_id) {
		companyDao.delete(company_id);
	}
	
//	public static Company afficherCompany(int company_id) {
//		return companyDao.getNameById(company_id);
//	}

}