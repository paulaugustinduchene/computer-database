package com.excilys.formation.java.cdb.dao;


import java.sql.Connection;
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

public class CompanyDaoImpl implements CompanyDao {

	
	private DaoConnexion daoConnexion;
	
	public CompanyDaoImpl( DaoConnexion daoConnexion) {
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
            resultat = statement.executeQuery("SELECT * FROM company;");

            while (resultat.next()) {

//                Company company = new Company();
//                
//                int id = resultat.getInt("id");
//                String name = resultat.getString("name");
//                     
//				company.setId(id );
//				company.setName(name);
                
                Company company = CompanyMapper.getCompany(resultat);

                companies.add(company);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return companies;
	}

}
