package edu.austincc.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import edu.austincc.db.Documentmanager;
import edu.austincc.domain.Document;

/**
 * Servlet implementation class SupportingDocumentServlet
 */
@WebServlet("/SupportingDocumentServlet")
public class SupportingDocumentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Resource(name = "jdbc/DB")
	DataSource ds;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SupportingDocumentServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url = "/WEB-INF/supportingDocumentsList.jsp";
		HttpSession session = request.getSession();
		int userId = (int) session.getAttribute("userId");

		ArrayList<Document> documentArray = new ArrayList<Document>();
		documentArray = new Documentmanager(ds)
				.listDocuments(userId, "SE_USER");
		if (documentArray != null) {
			request.setAttribute("documentArray", documentArray);
		}
		getServletContext().getRequestDispatcher(url)
				.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
