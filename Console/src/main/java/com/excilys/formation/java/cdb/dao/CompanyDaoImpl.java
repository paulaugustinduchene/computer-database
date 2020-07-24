package com.excilys.formation.java.cdb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.formation.java.cdb.beans.Company;
import com.excilys.formation.java.cdb.beans.Computer;
import com.excilys.formation.java.cdb.mapper.CompanyMapper;
import com.excilys.formation.java.cdb.mapper.ComputerMapper;
import com.excilys.formation.java.cdb.mapper.DateMapper;

@Repository
public class CompanyDaoImpl implements CompanyDao {

	@Autowired
	private DaoConnexion daoConnexion;
	
	@PersistenceContext
	private EntityManager entityManager;

	public CompanyDaoImpl(DaoConnexion daoConnexion) {
		this.daoConnexion = daoConnexion;
	}

	@Override
	public List<Company> list() {
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Company> criteriaQuery = cb.createQuery(Company.class);
		Root<Company> root = criteriaQuery.from(Company.class);

		criteriaQuery.select(root);

		TypedQuery<Company> companies = entityManager.createQuery(criteriaQuery);

		return companies.getResultList();

	}

	
	
	public void delete(int company_id) {

		//TODO replace with hibernate

        try {
        	Connection connexion = daoConnexion.getConnexion();
        	
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
