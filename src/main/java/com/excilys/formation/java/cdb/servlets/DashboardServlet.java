package com.excilys.formation.java.cdb.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.excilys.formation.java.cdb.beans.Computer;
import com.excilys.formation.java.cdb.services.ComputerServices;

/**
 * Servlet implementation class DashboardServlet
 */
@WebServlet("/listComputer")
@Controller
public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ComputerServices computerServices;

	private static Logger logger = LoggerFactory.getLogger(DashboardServlet.class);

	public int pageLength = 10;
	public int low = 0;
	public int high = pageLength;
	public int page = 1;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DashboardServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	private int maxpage(int computers, int perpage) {
		return computers / perpage;
	}

	private int setPageLow(int countperpage) {
		return low = (countperpage * page) - countperpage;
	}

	private int setPageHigh(int countperpage) {
		return high = (countperpage * page) - 1;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Computer> computers;

		int computerNb = computerServices.countComputers();

		if (request.getParameter("pageLength") != null) {
			pageLength = Integer.parseInt(request.getParameter("pageLength"));
		}

		int nbPage = maxpage(computerNb, pageLength);

		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}

		high = pageLength;

		if (request.getParameter("search") == null || request.getParameter("search").isEmpty()) {

			if (request.getParameter("orderAsc") == null) {
				computers = computerServices.afficherPage(setPageLow(pageLength), setPageHigh(pageLength));
			} else {
				computers = computerServices.orderByComputer();
			}

		} else {

			StringBuilder search = new StringBuilder("%");
			search.append(request.getParameter("search"));
			search.append("%");

			request.setAttribute("search", search);

			logger.info("search request is : " + search);

			computers = computerServices.search(search);
		}

		request.getParameter("count");
		request.setAttribute("count", computerNb);

		request.getParameter("List");
		request.setAttribute("List", computers);

		request.setAttribute("pageLength", pageLength);

		request.setAttribute("page", page);

		request.setAttribute("maxpage", nbPage);

		logger.info("maxpage = " + nbPage + "");

		this.getServletContext().getRequestDispatcher("/views/dashboard.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getParameter("selection") != null && !request.getParameter("selection").equals("")) {

			String ids = request.getParameter("selection");

			List<Integer> listId = new ArrayList<Integer>();

			for (String idd : ids.split(",")) {
				listId.add(Integer.parseInt(idd));
			}

			for (Integer computerId : listId) {
				computerServices.delete(new Computer.Builder().setIdComputer(computerId).build());
			}

		}

		doGet(request, response);
	}

}
