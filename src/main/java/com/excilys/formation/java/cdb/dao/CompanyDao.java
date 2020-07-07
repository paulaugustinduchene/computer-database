package com.excilys.formation.java.cdb.dao;

import java.util.List;

import com.excilys.formation.java.cdb.beans.Company;

public interface CompanyDao {

	public List<Company> list();
	public void delete(int company_id);
//	public Company getNameById(int id);
}
