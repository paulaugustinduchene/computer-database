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
import com.excilys.formation.java.cdb.beans.Computer;
import com.excilys.formation.java.cdb.cli.ClientInterface;
import com.excilys.formation.java.cdb.dto.ComputerDTO;
import com.excilys.formation.java.cdb.mapper.ComputerMapperDTO;
import com.excilys.formation.java.cdb.services.CompanyServices;
import com.excilys.formation.java.cdb.services.ComputerServices;

/**
 * Servlet implementation class AddComputerServlet
 */
@WebServlet("/addcomputer")
@Controller
public class AddComputerServlet extends HttpServlet {

	
	private Logger logger = LoggerFactory.getLogger(AddComputerServlet.class);

	private static final long serialVersionUID = 1L;

	
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
	public AddComputerServlet() {
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
		
		
		this.getServletContext().getRequestDispatcher("/views/addcomputer.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String name = null; 
		
		if(request.getParameter("computerName") != "") {
		 name = request.getParameter("computerName");
		// request.setAttribute("computerName", name);
		}
		
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

		System.out.println("name" +  name + " " + company_id);

		ComputerDTO computerDTO = new ComputerDTO(name, introduced, discontinued, company_id);

		if(request.getParameter("computerName") != "") {
		new ComputerMapperDTO();
		computerServices.create(ComputerMapperDTO.dtoToComputer(computerDTO));
		} else {
			logger.error("name field can't be empty ! ");
		}
		
		doGet(request, response);
	}

}
