package com.excilys.formation.java.cdb.cli;



import java.text.DateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.formation.java.cdb.beans.Computer;
import com.excilys.formation.java.cdb.dao.CompanyDaoImpl;
import com.excilys.formation.java.cdb.dao.DaoConnexion;
import com.excilys.formation.java.cdb.mapper.ComputerMapper;
import com.excilys.formation.java.cdb.mapper.DateMapper;
import com.excilys.formation.java.cdb.services.CompanyServices;
import com.excilys.formation.java.cdb.services.ComputerServices;


public class ClientInterface {
	
	private Logger logger = LoggerFactory.getLogger(ClientInterface.class);

	public void Appinit() {

		Scanner scan = new Scanner(System.in);

		
		
		boolean running = true;
		
		System.out.println(
				"   _____                            _              _____        _        ____                 \n"
						+ "  / ____|                          | |            |  __ \\      | |      |  _ \\                \n"
						+ " | |     ___  _ __ ___  _ __  _   _| |_ ___ _ __  | |  | | __ _| |_ __ _| |_) | __ _ ___  ___ \n"
						+ " | |    / _ \\| '_ ` _ \\| '_ \\| | | | __/ _ \\ '__| | |  | |/ _` | __/ _` |  _ < / _` / __|/ _ \\\n"
						+ " | |___| (_) | | | | | | |_) | |_| | ||  __/ |    | |__| | (_| | || (_| | |_) | (_| \\__ \\  __/\n"
						+ "  \\_____\\___/|_| |_| |_| .__/ \\__,_|\\__\\___|_|    |_____/ \\__,_|\\__\\__,_|____/ \\__,_|___/\\___|\n"
						+ "                       | |                                                                    \n"
						+ "                       |_|                                                                    \n"
						+ "");
		System.out.println("Computer Database CommandLine Acess \n " + "hit 'help' for help \n");

		// add here scaner or then args
		while (running == true) {

			System.out.print("computer_db >");
			String choice = scan.nextLine();
			switch (choice) {
			case "help":
				System.out.println("Help ! \n" + "commands available  : \n" + "computers > Display computer list \n"
						+ "companies > Display company list \n" + "create > create a computer \n"
						+ "delete > delete a computer \n" + "update > update a computer\n"
						+ "show > show details of a computer \n");
				break;
			case "computers":
				afficherPage();
				break;
			case "companies":
				break;
			case "show":
				showInformations();
				break;
			case "create":
				create();
				break;
			case "update":
				update();
				break;
			case "delete":
				delete();
				break;
			case "exit":
				running = false;
				System.out.println("GoodBye ! ");
				break;
			case "companyDel" :
				deleteCnie();
				break;
			}

		}

		// scan.close();

	}

	private Computer scanComputer() {

		Scanner scan = new Scanner(System.in);

		List<String> cmd = new ArrayList<String>();

		System.out.println("Enter the new computer specs : ");
		while (!scan.hasNext(";")) {
			String string = scan.next();

			cmd.add(string);
			System.out.println(string);
		}

		Computer computer = new Computer();

		try {
			computer.setName(cmd.get(0));
			computer.setIntroduced(DateMapper.stringToLocalDate(cmd.get(1)));
			computer.setDiscontinuted(DateMapper.stringToLocalDate(cmd.get(2)));
			computer.setCompany_id(Integer.parseInt(cmd.get(3)));
			computer.setId(Integer.parseInt(cmd.get(4)));
		} catch (Exception e){
			logger.error("Invalid entry for computer");
		}
		return computer;

	}

	private void create() {
		System.out.println("use £ as delimitter between entries"
				+ "follow this patern : 'name'  'date introdiuce'  'date discontinued'  'company id' 'id'"
				+ "dates should be yyyy-mm-dd");
		try {
		Computer computer = scanComputer();
		ComputerServices.create(computer);
		System.out.println("a new computer has been added to the database ");
		} catch(Exception e) {
			logger.error("Computer can't be created ! ");
		}
	}

	private void update() {
		Computer computer = scanComputer();
		ComputerServices.update(computer);
		System.out.println("computer has been update in the database ");
	}

	private void delete() {
		Computer computer = scanComputer();
		ComputerServices.delete(computer);
		System.out.println("computer has been erased from the database ");
	}

	private void showInformations() {
		System.out.print("Enter computer id to see informations \n" + "computer_db $id >");
		Scanner scan = new Scanner(System.in);
		int id = scan.nextInt();
		System.out.println(ComputerServices.showDetails(id - 1).toString());
	}
	
	private void deleteCnie() {
	System.out.print("Enter company id to delete \n" + "computer_db $id >");
	int company_id = new Scanner(System.in).nextInt();
	CompanyServices.delete(company_id);
	System.out.println("computer has been erased from the database ");
	}
	/**
	 * 
	 * Affiche la liste page par page les commandes
	 * 
	 */
	private void afficherPage() {
		Scanner scan = new Scanner(System.in);
		int low = 1;
		int high = 10;
		System.out.println(ComputerServices.afficherPage(low, high).toString());
		System.out.println("enter 'n' for next 'p' for previous and 'q' to exit list");
		
		while (!scan.next().equals("q")) {

			if(low <1 && high < 10) {
				low = 1; 
				high = 10;
			}
			
			switch (scan.next()) {
			case "n":
				low += 10;
				high += 10;
				System.out.println(ComputerServices.afficherPage(low, high).toString());
				break;
			case "p":
				low += -10;
				high += -10;
				if(low <1 && high < 10) {
					low = 1; 
					high = 10;
				}
				System.out.println(ComputerServices.afficherPage(low, high).toString());
				break;
			default:
				System.out.println("ceci n'est pas reconu");
			}

		}
	}
}