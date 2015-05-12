package edu.austincc.servlet;

import java.io.IOException;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import edu.austincc.db.AddressManager;
import edu.austincc.db.ElecCommuManager;
import edu.austincc.db.UsersManager;
import edu.austincc.domain.Address;
import edu.austincc.domain.ElecCommu;
import edu.austincc.domain.User;

/**
 * Servlet implementation class SignupServlet
 */
@WebServlet({ "/SignupSaveServlet", "/signupsave" })
public class SignupSaveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource(name="jdbc/DB")
	DataSource ds;

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignupSaveServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	  
        
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String url = "/signup.jsp";	
		String email = request.getParameter("email");
		
		String password  = request.getParameter("password") ;
		String password1 = request.getParameter("password1") ;
		String name  = request.getParameter("name") ;
		String delivery = request.getParameter("delivery") ;
		String city  = request.getParameter("city") ;
		String state = request.getParameter("state") ;
		String country = request.getParameter("country") ;
		String zip = request.getParameter("zip") ;
		String phonenumber  = request.getParameter("phonenumber") ;
		String todonate  = request.getParameter("todonate") ;
		String applyforhelp = request.getParameter("applyforhelp") ;
		String tovolunteer = request.getParameter("tovolunteer") ;
		//Date passwordExpiry = addYear(3);
		String error = null;	
		User validateUser = new UsersManager(ds).getUser(email);
		
		if (validateUser == null) {
			Address address = new Address(0, delivery, city, state, country, zip);
			int addressId = new AddressManager(ds).addAddress(address);
			
			ElecCommu phone = new ElecCommu(0, "PHONE", Integer.parseInt(phonenumber));
			int elecCommuPhoneId = new ElecCommuManager(ds).addElecCommu(phone);
			
			User user = new User(0, email, name, password, null, null, addressId, elecCommuPhoneId);
			
			int userId = new UsersManager(ds).addUser(user);
			HttpSession session = request.getSession();
			session.setAttribute("isLoggedIn", true); 
			session.setAttribute("userName", name);
			session.setAttribute("role", "USER");			
			url = "/index.jsp";
		} else {
			
			request.setAttribute("error", error);
			request.setAttribute("email", email);
			request.setAttribute("name", name);
			request.setAttribute("delivery", delivery);
			request.setAttribute("city", city);
			request.setAttribute("state", state);
			request.setAttribute("phonenumber", phonenumber);
			request.setAttribute("todonate", todonate);
			request.setAttribute("tovolunteer", tovolunteer);
			request.setAttribute("applyforhelp", applyforhelp);
		}
		
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}



	

}