package com.excilys.formation.java.cdb.spring;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration
public class SpringConfig implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext webContext = new AnnotationConfigWebApplicationContext();
		webContext.register(SpringConfig.class, SpringMvcConfig.class, HibernateConfig.class, ServicesConfig.class);
		webContext.setServletContext(servletContext);
		ServletRegistration.Dynamic servlet = servletContext.addServlet("dynamicServlet",
				new DispatcherServlet(webContext));
		servlet.setLoadOnStartup(1);
		servlet.addMapping("/");
	}


}
