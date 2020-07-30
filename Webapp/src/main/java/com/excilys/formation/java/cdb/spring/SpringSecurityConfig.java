package com.excilys.formation.java.cdb.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import com.excilys.formation.java.cdb.dao.DaoConnexion;


@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DaoConnexion daoConnexion;

    @Bean
    public UserDetailsService userDetailsService() {
    	JdbcUserDetailsManager jdbcUserDM = new JdbcUserDetailsManager(daoConnexion.getDS());
    	//jdbcUserDM.createUser(User.withUsername("user1").password(passwordEncoder().encode("user1234")).disabled(false).authorities("USER").build());
        return jdbcUserDM; 
    }
    
    
    public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(16);
    }
    
    @Bean
	public DaoAuthenticationProvider authentificationProvider() throws Exception {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsServiceBean());
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}
	
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	auth.authenticationProvider(authentificationProvider());
    }
    
    
  
    protected void configure(HttpSecurity http) throws Exception {
	    http.authorizeRequests()
	    .antMatchers("/auth").permitAll()
	    .antMatchers("/").permitAll()
	    .antMatchers(HttpMethod.GET,"/listComputer").hasAnyRole("USER","ADMIN")
	    .antMatchers(HttpMethod.GET,"/editcomputer").hasRole("ADMIN")
	    .antMatchers(HttpMethod.GET, "/addcomputer").hasRole("ADMIN")
	    .antMatchers(HttpMethod.POST, "/listComputer").hasRole("ADMIN")
	    .and()
		.formLogin()
		.loginPage("/auth")
		.usernameParameter("username")
		.passwordParameter("password")
		.defaultSuccessUrl("/listComputer")	
		.and()
		.logout()
		.logoutUrl("/logout")
		.logoutSuccessUrl("/auth")	
		.and().exceptionHandling()
		.accessDeniedPage("/500.html")
		.and().csrf().disable();
	}
	
    
	
}
