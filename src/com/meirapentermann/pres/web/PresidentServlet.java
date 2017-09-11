package com.meirapentermann.pres.web;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.meirapentermann.presidents.President;
import com.meirapentermann.presidents.PresidentDAO;
import com.meirapentermann.presidents.PresidentFileDAO;

/**
 * Servlet implementation class PresidentServlet
 */
@WebServlet("/PresidentServlet")
public class PresidentServlet extends HttpServlet {
	private PresidentFileDAO dao;

	public void init() throws ServletException {
		dao = new PresidentFileDAO(getServletContext());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		Integer i = (Integer)session.getAttribute("i");
		if(i==null) {
			i = 1;
		}
		
		List<President> current = (List<President>)session.getAttribute("current");
		if(current==null) {
			current = dao.getFullList();
		}

		try {
			if ((isNotNullOrEmpty(request.getParameter("selection")))){
				String radioButton = request.getParameter("search");
				String userInput = request.getParameter("selection");
				switch(radioButton) {
				case "lastname":
					current = dao.filterByLastName(userInput);
					if (current.size() == 0) {
						current = dao.getFullList();
					}
					i = 1;
					break;
				case "firstname":
					current = dao.filterByFirstName(userInput);
					if (current.size() == 0) {
						current = dao.getFullList();
					}
					i = 1;
					break;
				case "party":
					current = dao.filterByParty(userInput);
					if (current.size() == 0) {
						current = dao.getFullList();
					}
					i = 1;
					break;
				case "term":
					current = dao.getFullList();
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
				current = dao.getFullList();
				i = 1;
			}
			else if (isNotNullOrEmpty(request.getParameter("next"))) {
				if (i == current.size()) {
					i = 1;
				} 
				else {
					++i;
				}
			} 
			else if (isNotNullOrEmpty(request.getParameter("previous"))) {
				if (i == 1) {
					i = current.size();
				} else {
					--i;
				}
			}
		} 
		catch (Exception e) {
			i = 1;
		}

		session.setAttribute("i", i);
		session.setAttribute("current", current);
		session.setAttribute("currentPresident", current.get(i - 1));
		request.getRequestDispatcher("/presidentdisplay.jsp").forward(request, response);

	}

	private boolean isNotNullOrEmpty(String op) {
		return op != null && op.length() > 0;
	}

}
