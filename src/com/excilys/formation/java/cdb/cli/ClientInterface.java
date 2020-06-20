package com.excilys.formation.java.cdb.cli;

import java.util.Scanner;

import com.excilys.formation.java.cdb.services.CompanyServices;
import com.excilys.formation.java.cdb.services.ComputerServices;

public class ClientInterface {

	private static ComputerServices comServ = new ComputerServices();
	private static Scanner scan = new Scanner(System.in);

	public void Appinit() {
		
		boolean running = true; 

		System.out.println("Computer Database CommandLine Acess \n " + "hit 'help' for help \n");

		// add here scaner or then args
 while(running == true) {
	 
	 	System.out.print("computer_db >");
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
			//System.out.println("la comande etait : " + choice);
			break;
		case "companies":
			//CompanyServices.afficherliste();
			System.out.println("la comande etait : " + choice);
			break;
		case "show":
			//ComputerServices.showDetails(6);
			System.out.println("la comande etait : " + choice);
			break;
		case "create":
			//ComputerServices.create(); 
			System.out.println("la comande etait : " + choice);
			break;
		case "update":
			//ComputerServices.delete();
			System.out.println("la comande etait : " + choice);
			break;
		case "delete":
			//ComputerServices.update();
			System.out.println("la comande etait : " + choice);
			break;
		case "exit":
			running = false;
			System.out.println("GoodBye ! ");
			break;
		}

	}
	
	}

	
	private void update() { 
	}
}
