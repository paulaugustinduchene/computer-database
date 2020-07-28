package com.excilys.formation.java.cdb.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.excilys.formation.java.cdb.services"} )
public class ServicesConfig {
	

}
