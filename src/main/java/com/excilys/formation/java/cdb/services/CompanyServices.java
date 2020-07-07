package com.excilys.formation.java.cdb.services;


import java.util.List;

import com.excilys.formation.java.cdb.beans.Company;
import com.excilys.formation.java.cdb.dao.DaoConnexion;

public class CompanyServices {

private static DaoConnexion daoconnexion = DaoConnexion.getInstance();
	
	public CompanyServices() {
		
	}

	public static List<Company> afficherliste() {
		List<Company> companies = daoconnexion.getCompanyDao().list();
		return companies;
	}
	
	public static void delete(int company_id) {
		daoconnexion.getCompanyDao().delete(company_id);
	}
	
//	public static Company afficherCompany(int company_id) {
//		return daoconnexion.getCompanyDao().getNameById(company_id);
//	}

}