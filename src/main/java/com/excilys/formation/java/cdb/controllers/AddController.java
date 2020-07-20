package com.excilys.formation.java.cdb.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.servlet.ModelAndView;

import com.excilys.formation.java.cdb.beans.Company;

import com.excilys.formation.java.cdb.dto.ComputerDTO;
import com.excilys.formation.java.cdb.dto.DashboardDTO;
import com.excilys.formation.java.cdb.mapper.ComputerMapperDTO;
import com.excilys.formation.java.cdb.services.CompanyServices;
import com.excilys.formation.java.cdb.services.ComputerServices;


@Controller
public class AddController {

	@Autowired
	private ComputerServices computerServices;

	@Autowired
	private CompanyServices companyServices;

	private static Logger logger = LoggerFactory.getLogger(DashboardController.class);

	@GetMapping("/addcomputer")
	public ModelAndView controllerDoGet(DashboardDTO dashboardDTO) {

		ModelAndView mv = new ModelAndView("addcomputer");

		List<Company> companies = companyServices.afficherliste();
		dashboardDTO.getListCompanies();
		mv.getModel().put("ListCompanies", companies);

		return mv;
	}
	
	@PostMapping("/addcomputer")
	public ModelAndView controllerDoPost(ComputerDTO computerDTO) {
		
		ModelAndView mv = new ModelAndView("redirect:addcomputer");
		
		String name = null;
		if(computerDTO.getName() != "") {
		name = computerDTO.getName();
		// request.setAttribute("computerName", name);
		}
		
		String introduced = null;
		String discontinued = null;

		if (computerDTO.getIntroduced() != "") {
			introduced = computerDTO.getIntroduced();
			// request.setAttribute("introduced", introduced);
		}
		
		if (computerDTO.getDiscontinued() != "") {
			discontinued = computerDTO.getDiscontinued();
			// request.setAttribute("discontinued", discontinued);
		}
		
		
		String company_id = computerDTO.getCompanyId();
		// request.setAttribute("companyId", company_id );

		System.out.println("name" +  name + " " + company_id);

		ComputerDTO computer = new ComputerDTO(name, introduced, discontinued, company_id);

		if(computerDTO.getName() != "") {
		computerServices.create(ComputerMapperDTO.dtoToComputer(computer));
		} else {
			logger.error("name field can't be empty ! ");
		}
			
		return mv;
		
	}

}
