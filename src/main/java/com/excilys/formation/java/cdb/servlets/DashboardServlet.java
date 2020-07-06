package com.excilys.formation.java.cdb.servlets;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.formation.java.cdb.beans.Computer;
import com.excilys.formation.java.cdb.services.ComputerServices;

/**
 * Servlet implementation class DashboardServlet
 */
@WebServlet("/listComputer")
public class DashboardServlet extends HttpServlet {

	private static Logger logger = LoggerFactory.getLogger(DashboardServlet.class);

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DashboardServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Computer> computers;

		int computerNb = ComputerServices.countComputers();
		
		

		request.getParameter("count");
		request.setAttribute("count", computerNb);

		if (request.getParameter("search") == null || request.getParameter("search").isEmpty()) {

			if (request.getParameter("orderAsc") == null) {
				computers = ComputerServices.afficherliste();

				request.getParameter("List");
				request.setAttribute("List", computers);

			} else {

				computers = ComputerServices.orderByComputer();

				request.getParameter("List");
				request.setAttribute("List", computers);
			}

		} else {

			StringBuilder search = new StringBuilder("%");
			search.append(request.getParameter("search"));
			search.append("%");

			request.setAttribute("search", search);

			logger.info("search request is : " + search);

			computers = ComputerServices.search(search);

			request.getParameter("List");
			request.setAttribute("List", computers);
		}

		this.getServletContext().getRequestDispatcher("/views/dashboard.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		if (request.getParameter("selection") != null && !request.getParameter("selection").equals("")) {

			String id = request.getParameter("selection");

			List<Integer> ListId = Stream.of(id.split(","))
					.map(Integer::parseInt).collect(Collectors.toList());

			for (Integer computerId : ListId) {
				ComputerServices.delete(new Computer.Builder().setIdComputer(computerId).build());
			}
		}

		doGet(request, response);
	}

}
