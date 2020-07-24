package com.excilys.formation.java.cdb.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FrontEndTest {
	
	public static void main(String[] args) {

	System.setProperty("webdriver.gecko.driver", "/home/paduchene/Bureau/geckodriver-v0.26.0-linux64/geckodriver");
    
    WebDriver driver = new FirefoxDriver();
    
    driver.get("http://localhost:8080/computer-database_maven/listComputer");
	
	}
	
}
