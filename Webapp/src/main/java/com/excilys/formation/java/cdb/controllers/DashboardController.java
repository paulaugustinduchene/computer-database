package com.excilys.formation.java.cdb.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.formation.java.cdb.beans.Computer;
import com.excilys.formation.java.cdb.dto.DashboardDTO;
import com.excilys.formation.java.cdb.services.ComputerServices;




@Controller
public class DashboardController {
	
	private static final long serialVersionUID = 1L;

	@Autowired
	private ComputerServices computerServices;

	private static Logger logger = LoggerFactory.getLogger(DashboardController.class);

	public int pageLength = 10;
	public int offset = 0;
	public int limit= pageLength;
	public int page = 1;
	
	private int maxpage(int computers, int perpage) {
		return computers / perpage;
	}

	private int setOffset(int countperpage) {
		return offset = (countperpage * page) - countperpage;
	}
	
	@GetMapping({"/listComputer"})
	public ModelAndView controllerDoGet(DashboardDTO dashboardDTO) {
		
		ModelAndView mv = new ModelAndView("dashboard");
		
		List<Computer> computers;
		
		int computerNb = computerServices.countComputers();

		if (dashboardDTO.getPageLength() != null) {
			pageLength = Integer.parseInt(dashboardDTO.getPageLength());
		}

		int nbPage = maxpage(computerNb, pageLength);

		if (dashboardDTO.getPage()!= null) {
			page = Integer.parseInt(dashboardDTO.getPage());
		}

		if (dashboardDTO.getSearch() == null || dashboardDTO.getSearch().isEmpty()) {

			if (dashboardDTO.getOrderAsc() == null) {
				limit = pageLength; 
				computers = computerServices.afficherPage(setOffset(pageLength), limit);
			} else {
				computers = computerServices.orderByComputer();
			}

		} else {

			StringBuilder search = new StringBuilder("%");
			search.append(dashboardDTO.getSearch());
			search.append("%");
			
			mv.getModel().put("search", search);

			logger.info("search request is : " + search);

			computers = computerServices.search(search,offset,limit);
			
		}
		
	

		dashboardDTO.getCount();
		mv.getModel().put("count", computerNb);

		dashboardDTO.getList();
		mv.getModel().put("List", computers);

		mv.getModel().put("pageLength", pageLength);

		mv.getModel().put("page", page);

		mv.getModel().put("maxpage", nbPage);

		logger.info("maxpage = " + nbPage + "");
		
		
		
		
		return mv;
		
	}
	
	@PostMapping("/listComputer")
	public ModelAndView controllerDoPost(@RequestParam List<Integer> selection) {
		
		ModelAndView mv = new ModelAndView("redirect:listComputer");
			
			for(Integer id : selection) {
				computerServices.delete(new Computer.Builder().setIdComputer(id).build());
			}
			
		return mv;
		
	}
	
}
