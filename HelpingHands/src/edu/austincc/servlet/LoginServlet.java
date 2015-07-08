package edu.austincc.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import edu.austincc.db.UsersManager;
import edu.austincc.domain.User;
import edu.austincc.utils.Owasp;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static int ITERATION_NUMBER = 1000;

	@Resource(name = "jdbc/DB")
	DataSource ds;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String nav = request.getParameter("nav");
		if (nav.equals("Y")) {
			HttpSession session = request.getSession();
			session.setAttribute("error", "");
		}
		String url = "/WEB-INF/signin.jsp";
		request.setAttribute("servlet", "login");
		request.getRequestDispatcher(url).forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String url = "/WEB-INF/signin.jsp";
		String error = null;
		HttpSession session = request.getSession();
		session.setAttribute("error", "");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String remember = request.getParameter("remember");
		boolean success = false;
		User validateUser = null;
		;
		try {
			validateUser = new UsersManager(ds).getUser(email);
			String digest, salt;
			digest = validateUser.getPassword();
			salt = validateUser.getSalt();
			Owasp owasp = new Owasp();
			byte[] bDigest = owasp.base64ToByte(digest);
	        byte[] bSalt = owasp.base64ToByte(salt);
	        byte[] proposedDigest = owasp.getHash(ITERATION_NUMBER, password, bSalt);
	 		if (Arrays.equals(proposedDigest, bDigest)) {
				success = true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			error = "Connection Refused";
		}

		if (remember != null) {
			Cookie loginEmailCookie = new Cookie("email", email);
			response.addCookie(loginEmailCookie);

			Cookie rememberCookie = new Cookie("remember", "YES");
			response.addCookie(rememberCookie);

		} else {
			Cookie loginEmailCookie = new Cookie("email", "");
			loginEmailCookie.setMaxAge(0);
			response.addCookie(loginEmailCookie);

			Cookie rememberCookie = new Cookie("remember", "");
			rememberCookie.setMaxAge(0);
			response.addCookie(rememberCookie);

		}

		if (success) {
			request.setAttribute("user", validateUser);
			session.setAttribute("isLoggedIn", true);
			session.setAttribute("userName", validateUser.getName());
			session.setAttribute("userId", validateUser.getUserId());
			session.setAttribute("role", validateUser.getRole());
			url = "/index.jsp";
			response.sendRedirect(request.getContextPath() + url);
		} else {

			error = "Invalid login credentials. Please try again.";
			session.setAttribute("error", error);
			request.getRequestDispatcher(url).forward(request, response);
		}

	}

}