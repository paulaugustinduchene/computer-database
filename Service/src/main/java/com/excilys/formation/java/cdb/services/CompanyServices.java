package com.excilys.formation.java.cdb.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.formation.java.cdb.beans.Company;
import com.excilys.formation.java.cdb.dao.CompanyDao;


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