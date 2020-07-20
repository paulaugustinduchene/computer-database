package com.excilys.formation.java.cdb.controllers;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.formation.java.cdb.beans.Company;
import com.excilys.formation.java.cdb.beans.Computer;
import com.excilys.formation.java.cdb.dto.ComputerDTO;
import com.excilys.formation.java.cdb.dto.DashboardDTO;
import com.excilys.formation.java.cdb.mapper.ComputerMapperDTO;
import com.excilys.formation.java.cdb.services.CompanyServices;
import com.excilys.formation.java.cdb.services.ComputerServices;

@Controller
public class EditController {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ComputerServices computerServices;

	@Autowired
	private CompanyServices companyServices;

	private static Logger logger = LoggerFactory.getLogger(EditController.class);

	@GetMapping("/editcomputer")
	public ModelAndView controllerDoGet(DashboardDTO dashboardDTO, ComputerDTO computerDTO) {

		ModelAndView mv = new ModelAndView("editComputer");

		List<Company> companies = companyServices.afficherliste();

		dashboardDTO.getListCompanies();
		computerDTO.getId();
		
		mv.getModel().put("ListCompanies", companies);
		mv.getModel().put("id", computerDTO.getId());

		return mv;
	}
	
	
	@PostMapping("/editcomputer")
	public ModelAndView controllerDoPost(ComputerDTO computerDTO , DashboardDTO dashboardDTO) {
		
		ModelAndView mv = new ModelAndView("redirect:editcomputer");
			
		String id = computerDTO.getId();
		
		@Valid
		String name = computerDTO.getName();

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

		logger.info("id : " + id + " name : " + name + " " + company_id);

		ComputerDTO computer = new ComputerDTO(name, introduced, discontinued, company_id);
		computerDTO.setId(id);
		
		System.out.println(computerDTO.getId()+ " " + computerDTO.getName());
		
		if (computerDTO.getName() != "") {
			computerServices.update(ComputerMapperDTO.dtoToComputer(computer));
			logger.info("computer updated");
		} else {
			logger.error("name field can't be empty ! ");
		}
	
		return mv;
		
	}

}
