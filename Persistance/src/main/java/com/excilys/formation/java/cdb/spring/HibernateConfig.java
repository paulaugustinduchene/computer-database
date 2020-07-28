package com.excilys.formation.java.cdb.spring;

import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.excilys.formation.java.cdb.dao"} )
public class HibernateConfig {
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(HikariDataSource dataSource) {
		LocalContainerEntityManagerFactoryBean em  = new LocalContainerEntityManagerFactoryBean();
	    em.setDataSource(dataSource);
	    em.setPackagesToScan(new String[] { "com.excilys.formation.java.cdb.beans" });
	 
	    JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
	    em.setJpaVendorAdapter(vendorAdapter);
	    em.setJpaProperties(additionalProperties());
	 
	    return em;
	}
	
	@Bean
	public HikariDataSource hikariDataSource() {
		return new HikariDataSource(new HikariConfig("/config.properties"));
	}
	
	
	@Bean
	public PlatformTransactionManager txManager(LocalContainerEntityManagerFactoryBean entityManagerFactory) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
	    transactionManager.setEntityManagerFactory(entityManagerFactory.getObject()); 
	    return transactionManager;
	}
	
	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
	    return new PersistenceExceptionTranslationPostProcessor();
	}
	
	
	Properties additionalProperties() {
	    Properties properties = new Properties();
	    properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
	    return properties;
	}

	

}
