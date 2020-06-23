package com.excilys.formation.java.cdb.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.excilys.formation.java.cdb.beans.Computer;
import com.excilys.formation.java.cdb.mapper.ComputerMapper;
import com.excilys.formation.java.cdb.mapper.DateMapper;

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

	            //while (resultat.next()) {
	            while (resultat.next()) {	
					Computer computer = ComputerMapper.getComputer(resultat);

	                computers.add(computer);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return computers;
	    
	}
	
	public List<Computer> listpage(int low, int high) {
		 List<Computer> computers = new ArrayList<Computer>();
	        Connection connexion = null;
	        PreparedStatement prepareStatement = null;
	        ResultSet resultat = null;

	        try {
	            connexion = daoConnexion.getConnexion();
	            prepareStatement = connexion.prepareStatement("SELECT * FROM computer WHERE  id >= ? AND  id <= ? ;");
	            prepareStatement.setInt(1, low);
	            prepareStatement.setInt(2, high);
	            resultat = prepareStatement.executeQuery();
	            while (resultat.next()) {	
					Computer comp = ComputerMapper.getComputer(resultat);

	                computers.add(comp);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return computers;
	    
	}


	@Override
	public void add(Computer computer) {
		
		Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoConnexion.getConnexion();
            preparedStatement = connexion.prepareStatement("INSERT INTO computer(name, introduced, discontinued, company_id) VALUES( ?,? , ? , ?);");
            preparedStatement.setString(1, computer.getName());
            preparedStatement.setDate(2, DateMapper.localDateTosqlDate(computer.getIntroduced()));
            preparedStatement.setDate(3, DateMapper.localDateTosqlDate(computer.getDiscontinuted()));
            preparedStatement.setInt(4, computer.getCompany_id());

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
            preparedStatement.setDate(3, DateMapper.localDateTosqlDate(computer.getIntroduced()));
            preparedStatement.setDate(4, DateMapper.localDateTosqlDate(computer.getDiscontinuted()));
            preparedStatement.setInt(5, computer.getCompany_id());
            preparedStatement.setInt(6, computer.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
	}


		

}

