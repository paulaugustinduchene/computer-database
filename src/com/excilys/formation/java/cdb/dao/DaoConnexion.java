package com.excilys.formation.java.cdb.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoConnexion{

		private static final String url = "jdbc:mysql://localhost:3308/computer-database-db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		private static final String username = "admincdb";
		private static final String password = "qwerty1234";
		
		
		public DaoConnexion() {

		}

		public static DaoConnexion getInstance() {
			try {
				Class.forName("com.mysql.jdbc.Driver");
		
			} catch (ClassNotFoundException e) {
				
			}
			
			DaoConnexion instance = new DaoConnexion();
			
			return instance;
			
		}
		
		
		public Connection getConnexion() throws SQLException{
			return DriverManager.getConnection(url, username, password);
		}
		
		
		public ComputerDao getComputerDao() {
			return new ComputerDaoImpl(this);
		}
		
		public CompanyDao getCompanyDao() {
			return new CompanyDaoImpl(this);
		}
		
}