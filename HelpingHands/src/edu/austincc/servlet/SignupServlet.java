package edu.austincc.servlet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.sql.DataSource;

import edu.austincc.db.AddressManager;
import edu.austincc.db.Documentmanager;
import edu.austincc.db.ElecCommuManager;
import edu.austincc.db.GenCdManager;
import edu.austincc.db.UsersManager;
import edu.austincc.domain.Address;
import edu.austincc.domain.Document;
import edu.austincc.domain.ElecctronicCommunication;
import edu.austincc.domain.GenCodes;
import edu.austincc.domain.User;
import edu.austincc.utils.Owasp;

/**
 * Servlet implementation class SignupServlet
 */
@WebServlet({ "/SignupServlet", "/signup" })
@MultipartConfig(maxFileSize = 16177215)
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static int ITERATION_NUMBER = 1000;

	@Resource(name = "jdbc/DB")
	DataSource ds;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SignupServlet() {
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

		showForm(request, response);

	}

	private void showForm(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String nav = request.getParameter("nav");
		if (nav != null && nav.equals("Y")) {
			HttpSession session = request.getSession();
			session.setAttribute("error", "");
			session.setAttribute("email", "");
			session.setAttribute("name", "");
			session.setAttribute("delivery", "");
			session.setAttribute("city", "");
			session.setAttribute("state", "");
			session.setAttribute("type", "");
			session.setAttribute("phonenumber", "");
			session.setAttribute("todonate", "");
			session.setAttribute("tovolunteer", "");
			session.setAttribute("applyforhelp", "");
			session.setAttribute("selectCountry", "");
			session.setAttribute("selectedState", "");
		}
		String url = "/WEB-INF/signup.jsp";
		GenCdManager gCM = new GenCdManager(ds);
		String country = "COUNTRY";
		ArrayList<GenCodes> countryList = gCM.getGenCodes(country);
		request.setAttribute("countryList", countryList);
		request.setAttribute("servlet", "SignupServlet");

		String state = "STATES";
		ArrayList<GenCodes> stateList = gCM.getGenCodes(state);
		request.setAttribute("stateList", stateList);
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

		String url = "/WEb-INF/signup.jsp";
		HttpSession session = request.getSession();
		String error = null;
		User validatedUser = null;

		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String password1 = request.getParameter("password1");
		String name = request.getParameter("name");
		String delivery = request.getParameter("delivery");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String country = request.getParameter("country");
		String zip = request.getParameter("zip");
		String type = request.getParameter("type");
		String phonenumber = request.getParameter("phonenumber");
		String todonate = request.getParameter("todonate");
		String applyforhelp = request.getParameter("applyforhelp");
		String tovolunteer = request.getParameter("tovolunteer");

		SecureRandom random = null;
		try {
			random = SecureRandom.getInstance("SHA1PRNG");
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        // Salt generation 64 bits long
        byte[] bSalt = new byte[8];
        random.nextBytes(bSalt);
        // Digest computation
        Owasp owasp = new Owasp();
        byte[] bDigest = null;
		try {
			bDigest = owasp.getHash(ITERATION_NUMBER,password,bSalt);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        String sDigest = owasp.byteToBase64(bDigest);
        String sSalt = owasp.byteToBase64(bSalt);
		
		// Password expires in 3 years
		Calendar cal = Calendar.getInstance();
		cal.add(cal.YEAR, 3);

		// DateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
		Date passwordExpiry = (cal.getTime());

		if (email != null) {
			
			if (!email.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
				error = "Invalid Email format";
			}
		}
		
		
		if (password == null || password1 == null) {
			error = "Password and Pasword confimation can not be spaces";
		}
		if (password.equals(password1)) {
			validatedUser = new UsersManager(ds).getUser(email);
		} else {
			error = "Password and Pasword confimation are not the same";
		}

		if (phonenumber.length() != 0 && !NumericValidation(phonenumber)) {
			error = "Invalid Work Phone";
		}		
		
		if (validatedUser == null && error == null) {
			Address address = new Address(0, delivery, city, state, country,
					zip);
			int addressId = new AddressManager(ds).addAddress(address);

			ElecctronicCommunication phone = new ElecctronicCommunication(0,
					"PHONE", phonenumber);
			int elecCommuPhoneId = new ElecCommuManager(ds).addElecCommu(phone);

			User user = new User(0, email, name, sDigest,sSalt, passwordExpiry,
					null, type, addressId, elecCommuPhoneId);

			int userId = new UsersManager(ds).addUser(user);
			if (userId > 0) {
				session.setAttribute("isLoggedIn", true);
				session.setAttribute("userName", name);
				session.setAttribute("userId", userId);
				session.setAttribute("role", "USER");
				// Upload supporting Documents
				int parentTableId = (int) session.getAttribute("userId");
				String userName = (String) session.getAttribute("userName");
				String parentTableName = "SE_USER";
				boolean success = false;

				InputStream inputStream = null; // input stream of the upload
												// file
				Part file = request.getPart("filename");
				if (file != null) {
					inputStream = file.getInputStream();
					cal = Calendar.getInstance();
					Date createDate = cal.getTime();

					Document document = new Document(0, file.getName(),
							file.getContentType(), null, userName, createDate,
							parentTableId, parentTableName);
					success = new Documentmanager(ds).addDocuemnt(document,
							inputStream);
				}
				response.sendRedirect("index.jsp?signupSuccess=Y");
			}
		} else {
			if (validatedUser != null && validatedUser.getUserId() > 0) {
				error = "Provided Email already exists in the system";
			}
			if (error == null) {
				error = "Sign-up Failed";
			}
			session.setAttribute("error", error);
			session.setAttribute("email", email);
			session.setAttribute("name", name);
			session.setAttribute("delivery", delivery);
			session.setAttribute("city", city);
			session.setAttribute("state", state);
			session.setAttribute("type", type);
			session.setAttribute("phonenumber", phonenumber);
			session.setAttribute("todonate", todonate);
			session.setAttribute("tovolunteer", tovolunteer);
			session.setAttribute("applyforhelp", applyforhelp);
			session.setAttribute("selectCountry", country);			
			session.setAttribute("selectedState", state);
			showForm(request, response);

		}

	}

	private boolean NumericValidation(String phonenumber) {		
		try {
			Long  phonenum = Long.valueOf(phonenumber) ;
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

}
