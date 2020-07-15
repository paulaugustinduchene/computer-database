package com.excilys.formation.java.cdb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.excilys.formation.java.cdb.beans.Computer;
import com.excilys.formation.java.cdb.dto.ComputerDTO;
import com.excilys.formation.java.cdb.mapper.ComputerMapper;
import com.excilys.formation.java.cdb.mapper.ComputerMapperDTO;
import com.excilys.formation.java.cdb.mapper.DateMapper;

@Repository
public class ComputerDaoImpl implements ComputerDao {

	@Autowired
	private DaoConnexion daoConnexion;

	private static Logger logger = LoggerFactory.getLogger(ComputerDaoImpl.class);

	public ComputerDaoImpl(DaoConnexion daoConnexion) {
		this.daoConnexion = daoConnexion;
	}
	
	@Override
	public List<Computer> list() {
		
		List<Computer> computersSelection = new ArrayList<Computer>();
		
		String sqlList = "SELECT id, name, introduced, discontinued, company_id FROM computer ;";

		NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(daoConnexion.getDS());
			
			RowMapper<Computer> rowMapper = new RowMapper<Computer>() {

				@Override
				public Computer mapRow(ResultSet rs, int rowNum) throws SQLException {
					
				
					Computer computer = new Computer.Builder().setIdComputer(rs.getInt("id")).setName(rs.getString("name"))
							.setIntroduced(DateMapper.sqlToLocalDate(rs.getDate("introduced")))
							.setDiscontinued(DateMapper.sqlToLocalDate(rs.getDate("discontinued")))
							.setCompany_id(rs.getInt("company_id"))
							.build();

					return computer;
				}
				
		
		};
		
		computersSelection = jdbcTemplate.query(sqlList,rowMapper);
		
		return computersSelection;

	}

	public List<Computer> listpage(int low, int high) {
		
		List<Computer> computersSelection = new ArrayList<Computer>();
		
		String sqlPage = "SELECT id, name, introduced, discontinued, company_id FROM computer ;";

		NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(daoConnexion.getDS());
			
			RowMapper<Computer> rowMapper = new RowMapper<Computer>() {

				@Override
				public Computer mapRow(ResultSet rs, int rowNum) throws SQLException {
					
				
					Computer computer = new Computer.Builder().setIdComputer(rs.getInt("id")).setName(rs.getString("name"))
							.setIntroduced(DateMapper.sqlToLocalDate(rs.getDate("introduced")))
							.setDiscontinued(DateMapper.sqlToLocalDate(rs.getDate("discontinued")))
							.setCompany_id(rs.getInt("company_id"))
							.build();
					

					
					return computer;
				}
				
		
		};
		
		computersSelection = jdbcTemplate.query(sqlPage,rowMapper);
		
		return computersSelection;
		

	}

	@Override
	public void add(Computer computer) {

		String sqlAdd = "INSERT INTO computer(name, introduced, discontinued, company_id) VALUES( ?, ? , ? , ?);";

		try {

			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("name", computer.getName());
			parameters.addValue("name", DateMapper.localDateTosqlDate(computer.getIntroduced()));
			parameters.addValue("name", DateMapper.localDateTosqlDate(computer.getDiscontinuted()));
			parameters.addValue("name", computer.getCompany_id());
			NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(daoConnexion.getDS());
			jdbcTemplate.update(sqlAdd, parameters);

		} catch (Exception e) {
			logger.error("error when updating computer");
			e.printStackTrace();
		}

	}

	@Override
	public void delete(Computer computer) {

		String sqlDelete = "DELETE FROM computer WHERE id = ?; ";

		try {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("name", computer.getId());
			NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(daoConnexion.getDS());
			jdbcTemplate.update(sqlDelete, parameters);

		} catch (DataAccessException e) {
			logger.error("Error when deleting computer");
			e.printStackTrace();
		}

	}
	
	
	
	@Override
	public void update(Computer computer) {
		String sqlUpdate = "UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ?  WHERE id = ?";

		try {

			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("name", computer.getName());
			parameters.addValue("introduced", DateMapper.localDateTosqlDate(computer.getIntroduced()));
			parameters.addValue("discontinued", DateMapper.localDateTosqlDate(computer.getDiscontinuted()));
			parameters.addValue("company_id", computer.getCompany_id());
			parameters.addValue("id", computer.getId());
			NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(daoConnexion.getDS());
			jdbcTemplate.update(sqlUpdate, parameters);

		} catch (DataAccessException e) {
			logger.error("Error when Update ");
			e.printStackTrace();
		}

	}

	
	
	public List<Computer> getByName(String search) {

		List<Computer> computersSelection = new ArrayList<Computer>();
		String sqlSearch = "SELECT id, name, introduced, discontinued, company_id FROM computer WHERE name LIKE ? ";

		NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(daoConnexion.getDS());

			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("name", search);
			
			RowMapper<Computer> rowMapper = new RowMapper<Computer>() {

				@Override
				public Computer mapRow(ResultSet rs, int rowNum) throws SQLException {
				
					ComputerDTO computerDTO = new ComputerDTO.Builder().setId(rs.getString("id")).setName(rs.getString("name"))
							.setIntroduced(rs.getString("introdiuced")).setDiscontinued(rs.getString("discontinued"))
							.setCompanyId(rs.getString("company_id"))
							.build();
					
					Computer computer = ComputerMapperDTO.dtoToComputer(computerDTO);
					
					return computer;
				}
				
		
		};
		
		computersSelection = jdbcTemplate.query(sqlSearch, parameters, rowMapper);
		
		return computersSelection;
	}

	
	
	
	
		
	public List<Computer> getByCompany(int company_id) {

		List<Computer> computersSelection = new ArrayList<Computer>();

		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultat = null;

		try {
			connexion = daoConnexion.getConnexion();
			preparedStatement = connexion.prepareStatement(
					"SELECT id, name, introduced, discontinued, company_id FROM computer WHERE company_id = ? ");
			preparedStatement.setInt(1, company_id);
			resultat = preparedStatement.executeQuery();

			while (resultat.next()) {
				Computer computer = ComputerMapper.getComputer(resultat);
				computersSelection.add(computer);
			}

		} catch (SQLException e) {
			logger.error("error in get by name SQL");
			e.printStackTrace();
		}
		return computersSelection;
	}
	
	
	
	
	public List<Computer> orderByComputer() {
		List<Computer> computers = new ArrayList<Computer>();
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;

		try {
			connexion = daoConnexion.getConnexion();
			statement = connexion.createStatement();
			resultat = statement
					.executeQuery("SELECT id, name, introduced, discontinued, company_id FROM computer ORDER BY name;");

			while (resultat.next()) {
				Computer computer = ComputerMapper.getComputer(resultat);

				computers.add(computer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return computers;

	}

	@Override
	public int countComputer() {

		String sqlCount = "SELECT count(id) FROM computer ";

		int computerNb  = 0; 
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(daoConnexion.getDS());

		computerNb = jdbcTemplate.queryForObject(sqlCount, Integer.class);

		return computerNb;
	}

}
