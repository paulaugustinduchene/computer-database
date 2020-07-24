package com.excilys.formation.java.cdb.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Component;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Component
public class DaoConnexion {


	private static HikariConfig config;
	private static HikariDataSource ds;

	public DaoConnexion() {

		config = new HikariConfig("/config.properties");
		ds = new HikariDataSource(config);
	
	}


	public Connection getConnexion() throws SQLException {
		return ds.getConnection();
	}

	public CompanyDao getCompanyDao() {
		return new CompanyDaoImpl(this);
	}
	
	public HikariDataSource getDS() {
		return ds; 
	}

}
