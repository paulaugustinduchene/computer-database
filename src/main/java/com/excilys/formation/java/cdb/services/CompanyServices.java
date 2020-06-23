package com.excilys.formation.java.cdb.services;


import java.util.List;

import com.excilys.formation.java.cdb.beans.Company;
import com.excilys.formation.java.cdb.dao.DaoConnexion;

public class CompanyServices {

private static DaoConnexion daoconnexion = new DaoConnexion();
	
	public CompanyServices() {
		
	}

	public static void afficherliste() {
		List<Company> companies = daoconnexion.getCompanyDao().list();
		System.out.println(companies);
	}
	

}