package com.excilys.formation.java.cdb.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.formation.java.cdb.cli.ClientInterface;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DaoConnexion {

	/*
	 * private static final String url =
	 * "jdbc:mysql://localhost:3306/computer-database-db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	 * private static final String username = "admincdb"; private static final
	 * String password = "qwerty1234";
	 */

	private static HikariConfig config;
	private static HikariDataSource ds;

	//private static Logger logger = LoggerFactory.getLogger(ClientInterface.class);

	public DaoConnexion() {

	}

	public static DaoConnexion getInstance() {

		config = new HikariConfig("/config.properties");
		ds = new HikariDataSource(config);
		DaoConnexion instance = new DaoConnexion();
		return instance;

		/*
		 * 
		 * try { Class.forName("com.mysql.cj.jdbc.Driver");
		 * 
		 * } catch (ClassNotFoundException e) {
		 * logger.error("failed to contact Driver for sql databse connection"); }
		 * 
		 * DaoConnexion instance = new DaoConnexion(); return instance;
		 */
	}

	public Connection getConnexion() throws SQLException {
		return ds.getConnection();
		// return DriverManager.getConnection(url, username, password);
	}

	public ComputerDao getComputerDao() {
		return new ComputerDaoImpl(this);
	}

	public CompanyDao getCompanyDao() {
		return new CompanyDaoImpl(this);
	}

}