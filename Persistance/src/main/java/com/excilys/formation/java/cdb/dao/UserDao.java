package com.excilys.formation.java.cdb.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.formation.java.cdb.beans.User;


public class UserDao {
	
	@PersistenceContext
	private EntityManager entityManager;	
	
	public User getUsernameByUsername(String username) {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<User> criteriaQuery = cb.createQuery(User.class);
		Root<User> root = criteriaQuery.from(User.class);

	//	return criteriaQuery.select(root).where();
		
		return null; 

	}

}
