package edu.austincc.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import edu.austincc.db.GenCdManager;
import edu.austincc.domain.GenCodes;

/**
 * Servlet implementation class SignupServlet
 */
@WebServlet({ "/SignupServlet", "/signup" })
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Resource(name="jdbc/DB")
	DataSource ds;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignupServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String url = "/WEB-INF/signup.jsp";
		GenCdManager gCM = new GenCdManager(ds);		
		String country = "COUNTRY";
		ArrayList<GenCodes> countryList = gCM.getGenCodes(country);
		request.setAttribute("countryList", countryList);

		String state = "STATES";
		ArrayList<GenCodes> stateList = gCM.getGenCodes(state);
		request.setAttribute("stateList", stateList);

		
		getServletContext().getRequestDispatcher(url).forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}
