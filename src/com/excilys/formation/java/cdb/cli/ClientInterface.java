package com.excilys.formation.java.cdb.cli;

import java.util.Scanner;

import com.excilys.formation.java.cdb.services.CompanyServices;
import com.excilys.formation.java.cdb.services.ComputerServices;

public class ClientInterface {

	private static ComputerServices comServ = new ComputerServices();
	private static Scanner scan = new Scanner(System.in); 

	public void Appinit() {

		System.out.println("Computer Database CommandLine Acess \n " + "hit 'help' for help \n");

		// add here scaner or then args

		String choice = scan.nextLine();
		switch (choice) {
		case "help":
			System.out.println("Help ! \n"
					+ "commands available  : \n"
					+ "computers > Display computer list \n"
					+ "companies > Display company list \n"
					+ "create > create a computer \n"
					+ "delete > delete a computer \n"
					+ "update > update a computer\n"
					+ "computer_show  show details of a computer \n");
			break;
		case "computers":
			ComputerServices.afficherliste();
			break;
		case "companies":
			CompanyServices.afficherliste();
			break;
		case "show":
			ComputerServices.showDetails();
		case "create":
			ComputerServices.create(); 
		case "update":
			ComputerServices.delete();
		case "delete":
			ComputerServices.update();
		}

	}
	
	

}
