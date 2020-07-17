package com.excilys.formation.java.cdb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

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
	
	
	private static Logger logger = LoggerFactory.getLogger(ComputerDaoImpl.class);
	
	@Autowired
	private DaoConnexion daoConnexion;

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public ComputerDaoImpl(DaoConnexion daoConnexion) {
		this.daoConnexion = daoConnexion;
	}
	
	@Override
	public List<Computer> list() {
		
		List<Computer> computersSelection = new ArrayList<Computer>();
		
		String sqlList = "SELECT id, name, introduced, discontinued, company_id FROM computer ;";
			
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
		
		computersSelection = namedParameterJdbcTemplate.query(sqlList,rowMapper);
		
		return computersSelection;

	}

	public List<Computer> listpage(int low, int high) {
		
		List<Computer> computersSelection = new ArrayList<Computer>();
		
		String sqlPage = "SELECT id, name, introduced, discontinued, company_id FROM computer WHERE id < :high AND id > :low ;";

		MapSqlParameterSource parameters = new MapSqlParameterSource(); 
		parameters.addValue("high", high);
		parameters.addValue("low", low);
			
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
		
		computersSelection = namedParameterJdbcTemplate.query(sqlPage,parameters,rowMapper);
		
		return computersSelection;
		

	}

	@Override
	public void add(Computer computer) {

		String sqlAdd = "INSERT INTO computer(name, introduced, discontinued, company_id) VALUES( :name, :introduced , :discontinued , :company_id);";

		try {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("name", computer.getName());
			parameters.addValue("introduced", DateMapper.localDateTosqlDate(computer.getIntroduced()));
			parameters.addValue("discontinued", DateMapper.localDateTosqlDate(computer.getDiscontinuted()));
			parameters.addValue("company_id", computer.getCompany_id());
			namedParameterJdbcTemplate.update(sqlAdd, parameters);

		} catch (Exception e) {
			logger.error("error when updating computer");
			e.printStackTrace();
		}

	}

	
	@Override
	public void delete(Computer computer) {

		String sqlDelete = "DELETE FROM computer WHERE id = :id ; ";

		try {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("id", computer.getId());
			jdbcTemplate.update(sqlDelete, parameters);

		} catch (DataAccessException e) {
			logger.error("Error when deleting computer");
			e.printStackTrace();
		}

	}
	
	
	
	@Override
	public void update(Computer computer) {
		String sqlUpdate = "UPDATE computer SET :name , :introduced, :discontinued, :company_id  WHERE id = :id ";

		try {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("name", computer.getName());
			parameters.addValue("introduced", DateMapper.localDateTosqlDate(computer.getIntroduced()));
			parameters.addValue("discontinued", DateMapper.localDateTosqlDate(computer.getDiscontinuted()));
			parameters.addValue("company_id", computer.getCompany_id());
			parameters.addValue("id", computer.getId());
			namedParameterJdbcTemplate.update(sqlUpdate, parameters);

		} catch (DataAccessException e) {
			logger.error("Error when Update ");
			e.printStackTrace();
		}
	}

	
	
	public List<Computer> getByName(String search) {

		List<Computer> computersSelection = new ArrayList<Computer>();
		
		String sqlSearch = "SELECT id, name, introduced, discontinued, company_id FROM computer WHERE name LIKE :search ";
		
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		
		parameters.addValue("search", search);
		
			
			RowMapper<Computer> rowMapper = new RowMapper<Computer>() {

				@Override
				public Computer mapRow(ResultSet rs, int rowNum) throws SQLException {
				
					ComputerDTO computerDTO = new ComputerDTO.Builder().setId(rs.getString("id")).setName(rs.getString("name"))
							.setIntroduced(rs.getString("introduced")).setDiscontinued(rs.getString("discontinued"))
							.setCompanyId(rs.getString("company_id"))
							.build();
					
					Computer computer = ComputerMapperDTO.dtoToComputer(computerDTO);
					
					return computer;
				}
		};
		
		computersSelection = namedParameterJdbcTemplate.query(sqlSearch, parameters, rowMapper);
		
		return computersSelection;
	}
	
	
	public List<Computer> getByCompany(int company_id) {

		List<Computer> computersSelection = new ArrayList<Computer>();
		
		String sqlSearch = "SELECT id, name, introduced, discontinued, company_id FROM computer WHERE company_id = :companyId ";
				
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		
		parameters.addValue("companyId", company_id);
		
			
			RowMapper<Computer> rowMapper = new RowMapper<Computer>() {

				@Override
				public Computer mapRow(ResultSet rs, int rowNum) throws SQLException {
				
					ComputerDTO computerDTO = new ComputerDTO.Builder().setId(rs.getString("id")).setName(rs.getString("name"))
							.setIntroduced(rs.getString("introduced")).setDiscontinued(rs.getString("discontinued"))
							.setCompanyId(rs.getString("company_id"))
							.build();
					
					Computer computer = ComputerMapperDTO.dtoToComputer(computerDTO);
					
					return computer;
				}
		};
		
		computersSelection = namedParameterJdbcTemplate.query(sqlSearch, parameters, rowMapper);
		
		return computersSelection;
	}
	

	
	public List<Computer> orderByComputer() {
		List<Computer> computers = new ArrayList<Computer>();

		String sqlOrderAsc = "SELECT id, name, introduced, discontinued, company_id FROM computer ORDER BY name;";
			
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
		
		computers= jdbcTemplate.query(sqlOrderAsc,rowMapper);

		return computers;
	}

	

	@Override
	public int countComputer() {

		String sqlCount = "SELECT count(id) FROM computer ";

		int computerNb  = 0; 

		computerNb = jdbcTemplate.queryForObject(sqlCount, Integer.class);

		return computerNb;
	}

}
