package com.excilys.formation.java.cdb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.excilys.formation.java.cdb.beans.Computer;
import com.excilys.formation.java.cdb.mapper.ComputerMapper;

public class ComputerDaoImpl implements ComputerDao{
	
	private DaoConnexion daoConnexion;
	
	public ComputerDaoImpl( DaoConnexion daoConnexion) {
        this.daoConnexion = daoConnexion;
    }

	@Override
	public List<Computer> list() {
		 List<Computer> computers = new ArrayList<Computer>();
	        Connection connexion = null;
	        Statement statement = null;
	        ResultSet resultat = null;

	        try {
	            connexion = daoConnexion.getConnexion();
	            statement = connexion.createStatement();
	            resultat = statement.executeQuery("SELECT * FROM computer;");

	            while (resultat.next()) {

//	                Computer computer = new Computer();
//	                
//	                int id = resultat.getInt("id");
//	                String name = resultat.getString("name");
//	                Date introduced = resultat.getDate("introduced");
//	                Date discontinuted = resultat.getDate("discontinued");
//	                int company_id = resultat.getInt("company_id");
//	                
//					computer.setId(id );
//					computer.setName(name);
//					computer.setIntroduced(introduced);
//					computer.setDiscontinuted(discontinuted);
//					computer.setCompany_id(company_id);
					
					Computer computer = ComputerMapper.getComputer(resultat);

	                computers.add(computer);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return computers;
	    
	}

	@Override
	public void create(Computer computer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void add(Computer computer) {
		
		Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoConnexion.getConnexion();
            preparedStatement = connexion.prepareStatement("INSERT INTO computer(id, name, introduced, discontinued, company_id) VALUES(?, ?,? , ? , ?);");
            preparedStatement.setInt(1, computer.getId());
            preparedStatement.setString(2, computer.getName());
            preparedStatement.setDate(3, (java.sql.Date) computer.getIntroduced());
            preparedStatement.setDate(4, (java.sql.Date) computer.getDiscontinuted());
            preparedStatement.setInt(5, computer.getCompany_id());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

	}

	@Override
	public void delete(Computer computer) {

		Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoConnexion.getConnexion();
            preparedStatement = connexion.prepareStatement("DELETE FROM computer WHERE id = ?; ");
            preparedStatement.setInt(1, computer.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
	}

	@Override
	public void update(Computer computer) {
		Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoConnexion.getConnexion();
            preparedStatement = connexion.prepareStatement("UPDATE computer SET id = ?, name = ?, introduced = ?, discontinued = ?, company_id = ?  WHERE id = ?");
            preparedStatement.setInt(1, computer.getId());
            preparedStatement.setString(2, computer.getName());
            preparedStatement.setDate(3, (java.sql.Date) computer.getIntroduced());
            preparedStatement.setDate(4, (java.sql.Date) computer.getDiscontinuted());
            preparedStatement.setInt(5, computer.getCompany_id());
            preparedStatement.setInt(6, computer.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
	}
		

}
