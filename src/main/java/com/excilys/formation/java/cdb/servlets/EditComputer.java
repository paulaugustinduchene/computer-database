package com.excilys.formation.java.cdb.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.excilys.formation.java.cdb.beans.Company;
import com.excilys.formation.java.cdb.dto.ComputerDTO;
import com.excilys.formation.java.cdb.mapper.ComputerMapperDTO;
import com.excilys.formation.java.cdb.services.CompanyServices;
import com.excilys.formation.java.cdb.services.ComputerServices;

/**
 * Servlet implementation class EditComputer
 */
@WebServlet("/editcomputer")
@Controller
public class EditComputer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Logger logger = LoggerFactory.getLogger(AddComputerServlet.class);
	
	@Autowired
	private ComputerServices computerServices;
	
	@Autowired
	private CompanyServices companyServices;

    public void init(ServletConfig config) throws ServletException
    {
    	super.init(config);
    	SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }
	
	

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditComputer() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Company> companies = companyServices.afficherliste();

		request.getParameter("ListCompanies");
		request.setAttribute("ListCompanies", companies);

		request.getParameter("id");
		request.setAttribute("id", request.getParameter("id"));

		this.getServletContext().getRequestDispatcher("/views/editComputer.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("id");

		String name = request.getParameter("computerName");

		String introduced = null;
		String discontinued = null;

		if (request.getParameter("introduced") != "") {
			introduced = request.getParameter("introduced");
			// request.setAttribute("introduced", introduced);
		}
		if (request.getParameter("discontinued") != "") {
			discontinued = request.getParameter("discontinued");
			// request.setAttribute("discontinued", discontinued);
		}
		String company_id = request.getParameter("companyId");
		// request.setAttribute("companyId", company_id );

		logger.info("id : " + id + " name : " + name + " " + company_id);

		ComputerDTO computerDTO = new ComputerDTO(name, introduced, discontinued, company_id);
		computerDTO.setId(id);
		System.out.println(computerDTO.getId()+ " " + computerDTO.getName());
		if (request.getParameter("computerName") != "") {
			computerServices.update(new ComputerMapperDTO().dtoToComputer(computerDTO));
			logger.info("computer updated");
		} else {
			logger.error("name field can't be empty ! ");
		}

		doGet(request, response);
	}

}
