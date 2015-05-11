package edu.austincc.servlet;

import java.io.IOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
		String email = request.getParameter("email");
		String password = request.getParameter("password") ;		
		String error = null;	
		User validateUser = new UsersManager(ds).getUser(email, password);

		if (validateUser != null) {
				request.setAttribute("user", validateUser);

				HttpSession session = request.getSession();
				session.setAttribute("isLoggedIn", true); 
				session.setAttribute("userName", validateUser.getName());
				session.setAttribute("role", validateUser.getRole());
				url = "/index.jsp";
		} else {
			
			error = "Invalid login credentials. Please try again.";
			request.setAttribute("error", error);
			request.setAttribute("email", email);
		}
			
		
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}

	

}