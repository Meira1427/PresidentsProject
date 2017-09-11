package com.meirapentermann.pres.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.meirapentermann.presidents.PresidentDAO;
import com.meirapentermann.presidents.PresidentFileDAO;

/**
 * Servlet implementation class PresidentServlet
 */
@WebServlet("/PresidentServlet")
public class PresidentServlet extends HttpServlet {
	private PresidentFileDAO dao;
	private int i = 1;

	public void init() throws ServletException {
		dao = new PresidentFileDAO(getServletContext());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			if ((isNotNullOrEmpty(request.getParameter("selection")))){
				String radioButton = request.getParameter("search");
				String userInput = request.getParameter("selection");
				switch(radioButton) {
				case "lastname":
					dao.setCurrent(dao.filterByLastName(userInput));
					if (dao.getCurrent().size() == 0) {
						dao.setCurrent(dao.getFullList());
					}
					i = 1;
					break;
				case "firstname":
					dao.setCurrent(dao.filterByFirstName(userInput));
					if (dao.getCurrent().size() == 0) {
						dao.setCurrent(dao.getFullList());
					}
					i = 1;
					break;
				case "party":
					dao.setCurrent(dao.filterByParty(userInput));
					if (dao.getCurrent().size() == 0) {
						dao.setCurrent(dao.getFullList());
					}
					i = 1;
					break;
				case "term":
					dao.setCurrent(dao.getFullList());
					i = Integer.parseInt(userInput);
					if (i <= 0 || i > (dao.getFullList().size())) {
						i = 1;
					}
					break;
				default:
					i = 1;
				}
			} 
			else if ((isNotNullOrEmpty(request.getParameter("full")))) {
				dao.setCurrent(dao.getFullList());
				i = 1;
			}
			else if (isNotNullOrEmpty(request.getParameter("next"))) {
				if (i == dao.getCurrent().size()) {
					i = 1;
				} 
				else {
					++i;
				}
			} 
			else if (isNotNullOrEmpty(request.getParameter("previous"))) {
				if (i == 1) {
					i = dao.getCurrent().size();
				} else {
					--i;
				}
			}
		} 
		catch (Exception e) {
			i = 1;
		}

		request.setAttribute("currentPresident", dao.getCurrent().get(i-1));
		request.getRequestDispatcher("/presidentdisplay.jsp").forward(request, response);

	}

	private boolean isNotNullOrEmpty(String op) {
		return op != null && op.length() > 0;
	}

}
