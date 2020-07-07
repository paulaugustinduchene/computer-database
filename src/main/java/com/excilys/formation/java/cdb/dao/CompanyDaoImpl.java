package com.excilys.formation.java.cdb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.excilys.formation.java.cdb.beans.Company;
import com.excilys.formation.java.cdb.beans.Computer;
import com.excilys.formation.java.cdb.mapper.CompanyMapper;
import com.excilys.formation.java.cdb.mapper.ComputerMapper;
import com.excilys.formation.java.cdb.mapper.DateMapper;

public class CompanyDaoImpl implements CompanyDao {

	private DaoConnexion daoConnexion;

	public CompanyDaoImpl(DaoConnexion daoConnexion) {
		this.daoConnexion = daoConnexion;
	}

	@Override
	public List<Company> list() {
		List<Company> companies = new ArrayList<Company>();
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;

		try {
			connexion = daoConnexion.getConnexion();
			statement = connexion.createStatement();
			resultat = statement.executeQuery("SELECT id, name FROM company;");

			while (resultat.next()) {

				Company company = CompanyMapper.getCompany(resultat);

				companies.add(company);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return companies;
	}

	
	
	public void delete(int company_id) {

		

        try {
        	Connection connexion = DaoConnexion.getInstance().getConnexion();
        	
            PreparedStatement computerStatement = connexion.prepareStatement("DELETE FROM computer WHERE company_id = ?;");
            computerStatement.setInt(1, company_id);
            computerStatement.executeUpdate();
        		
            PreparedStatement companyStatement = connexion.prepareStatement("DELETE FROM company WHERE id = ?; ");
            companyStatement.setInt(1, company_id);
            companyStatement.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
	}
	
	
	
	
	
	
	
	

//	public Company getNameById(int id) {
//	Company company = new Company();
//        Connection connexion = null;
//        PreparedStatement prepareStatement = null;
//        ResultSet resultat = null;
//
//        try {
//            connexion = daoConnexion.getConnexion();
//            prepareStatement = connexion.prepareStatement("SELECT id, name FROM company WHERE  id = ? ;");
//            prepareStatement.setInt(1, id);
//            resultat = prepareStatement.executeQuery();
//            while (resultat.next()) {	
//				company = CompanyMapper.getCompany(resultat);
//				
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return company;
//	}

}
