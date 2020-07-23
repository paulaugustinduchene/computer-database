package com.excilys.formation.java.cdb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.management.Query;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.sql.DataSource;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.formation.java.cdb.beans.Computer;
import com.excilys.formation.java.cdb.dto.ComputerDTO;
import com.excilys.formation.java.cdb.mapper.ComputerMapper;
import com.excilys.formation.java.cdb.mapper.ComputerMapperDTO;
import com.excilys.formation.java.cdb.mapper.DateMapper;

@Repository
public class ComputerDaoImpl implements ComputerDao {

	private static Logger logger = LoggerFactory.getLogger(ComputerDaoImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	public ComputerDaoImpl(DaoConnexion daoConnexion) {
		this.daoConnexion = daoConnexion;
	}

	
	
	@Override
	public List<Computer> list() {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Computer> criteriaQuery = cb.createQuery(Computer.class);
		Root<Computer> root = criteriaQuery.from(Computer.class);

		criteriaQuery.select(root);

		TypedQuery<Computer> computers = entityManager.createQuery(criteriaQuery);

		return computers.getResultList();

	}

	public List<Computer> listpage(int low, int high) {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Computer> criteriaQuery = cb.createQuery(Computer.class);
		Root<Computer> root = criteriaQuery.from(Computer.class);

		criteriaQuery.select(root);

		TypedQuery<Computer> computers = entityManager.createQuery(criteriaQuery);

		return computers.getResultList();



	}

	@Transactional
	@Override
	public void add(Computer computer) {
		
		entityManager.persist(computer);

	}

	
	@Transactional
	@Override
	public void delete(Computer computer) {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaDelete<Computer> criteriaQuery = cb.createCriteriaDelete(Computer.class);
		Root<Computer> root = criteriaQuery.from(Computer.class);
		
		criteriaQuery.where(cb.equal(root.get("id"), computer.getId()));
		entityManager.createQuery(criteriaQuery).executeUpdate();
	}

	
	@Transactional
	@Override
	public void update(Computer computer) {
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaUpdate<Computer> criteriaQuery = cb.createCriteriaUpdate(Computer.class);
		Root<Computer> root = criteriaQuery.from(Computer.class);

		criteriaQuery.set(root.get("name"), computer.getName());
		
		criteriaQuery.set(root.get("introduced"), computer.getIntroduced());
		
		criteriaQuery.set(root.get("discontinued"), computer.getDiscontinued());
		
		criteriaQuery.set(root.get("company_id"), computer.getDiscontinued());
		
		criteriaQuery.where(cb.equal(root.get("id"), computer.getId()));
		
		this.entityManager.createQuery(criteriaQuery).executeUpdate();

	}

	public List<Computer> getByName(String search) {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Computer> criteriaQuery = cb.createQuery(Computer.class);
		Root<Computer> root = criteriaQuery.from(Computer.class);

		Predicate computerName = cb.like(root.get("name"), search);

		criteriaQuery.select(root).where(cb.or(computerName));

		TypedQuery<Computer> computers = entityManager.createQuery(criteriaQuery);

		return computers.getResultList();

	}

	
	
	
	public List<Computer> getByCompany(int company_id) {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Computer> criteriaQuery = cb.createQuery(Computer.class);
		Root<Computer> root = criteriaQuery.from(Computer.class);

		criteriaQuery.select(root).where(root.equals("company_id"),company_id);

		TypedQuery<Computer> computers = entityManager.createQuery(criteriaQuery);

		return computers.getResultList();
	}

	public List<Computer> orderByComputer() {

		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Computer> criteriaQuery = cb.createQuery(Computer.class);
		Root<Computer> root = criteriaQuery.from(Computer.class);
		criteriaQuery.orderBy(cb.asc(root.get("name")));

		TypedQuery <Computer> computers = entityManager.createQuery(criteriaQuery);
		return computers.getResultList();

	}

	
	
	
	@Transactional
	@Override
	public int countComputer() {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = cb.createQuery(Long.class);
		Root<Computer> root = criteriaQuery.from(Computer.class);
		criteriaQuery.select(cb.count(root));

		return entityManager.createQuery(criteriaQuery).getSingleResult().intValue();

	}

}
