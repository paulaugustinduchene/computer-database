package com.excilys.formation.java.cdb.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import com.excilys.formation.java.cdb.dao.DaoConnexion;
import com.excilys.formation.java.cdb.dao.UserDao;

public class UserServices implements UserDetailsService{
    
	
	@Autowired
	private UserDao userdao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		userdao.getUsernameByUsername(username);
		return null;
		
	}
}
