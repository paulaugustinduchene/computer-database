package com.excilys.formation.java.cdb.spring;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.AbstractContextLoaderInitializer;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.excilys.formation.java.cdb.dao.DaoConnexion;

@Configuration
@ComponentScan(basePackages = {"com.excilys.formation.java.cdb.dao","com.excilys.formation.java.cdb.services","com.excilys.formation.java.cdb.cli"} )
public class SpringConfig implements WebApplicationInitializer 
{

	 @Bean
		public NamedParameterJdbcTemplate setDataSourceNamed(DaoConnexion daoConnexion) {
		    return  new NamedParameterJdbcTemplate(daoConnexion.getDS());
		}
	 
	 @Bean
	 public JdbcTemplate setDataSourceJdbcTemplate(DaoConnexion daoConnexion) {
		    return  new JdbcTemplate(daoConnexion.getDS());
		}

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext webContext = new AnnotationConfigWebApplicationContext();
		webContext.register(SpringConfig.class,SpringMvcConfig.class);
		webContext.setServletContext(servletContext);
		ServletRegistration.Dynamic servlet = servletContext.addServlet("dynamicServlet", new DispatcherServlet(webContext));
		servlet.setLoadOnStartup(1);
		servlet.addMapping("/");
		
	}

}
