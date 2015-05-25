package edu.austincc.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

 

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet({ "/LoginServlet", "/login" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Resource(name="jdbc/DB")
	DataSource ds;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  
    
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String url = "/signin.jsp";
		String error = null;
		HttpSession session = request.getSession();
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String remember = request.getParameter("remember") ;
		User validateUser = new UsersManager(ds).getUser(email, password);

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
		
		
		if (validateUser != null) {
				request.setAttribute("user", validateUser);
				session.setAttribute("isLoggedIn", true); 
				session.setAttribute("userName", validateUser.getName());
				session.setAttribute("role", validateUser.getRole());
				url = "/index.jsp";				
				//response.sendRedirect(url);
		} else {
			
			error = "Invalid login credentials. Please try again.";
			request.setAttribute("error", error);
		}					
		//getServletContext().getRequestDispatcher(url).forward(request, response);
		response.sendRedirect(request.getContextPath() + url);
	}

	

}