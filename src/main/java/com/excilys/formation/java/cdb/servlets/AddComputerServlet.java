package com.excilys.formation.java.cdb.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.formation.java.cdb.beans.Company;
import com.excilys.formation.java.cdb.beans.Computer;
import com.excilys.formation.java.cdb.dto.ComputerDTO;
import com.excilys.formation.java.cdb.mapper.ComputerMapperDTO;
import com.excilys.formation.java.cdb.services.CompanyServices;
import com.excilys.formation.java.cdb.services.ComputerServices;

/**
 * Servlet implementation class AddComputerServlet
 */
@WebServlet("/addcomputer")
public class AddComputerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddComputerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Company> companies = CompanyServices.afficherliste(); 
		request.getParameter("ListCompanies");
		request.setAttribute("ListCompanies", companies);
		this.getServletContext().getRequestDispatcher("/views/addcomputer.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		String name = request.getParameter("computerName");
        request.setAttribute("computerName", name);
        String introduced = request.getParameter("introduced");
        request.setAttribute("introduced", introduced);
        String discontinued = request.getParameter("discontinued");
        request.setAttribute("discontinued", discontinued);
        String company_id = request.getParameter("companyId");
        request.setAttribute("companyId", company_id );
        
        ComputerDTO computerDTO = new ComputerDTO(name, introduced, discontinued, company_id);
        
        ComputerServices.create(new ComputerMapperDTO().dtoToComputer(computerDTO));
        
        
	}

}
