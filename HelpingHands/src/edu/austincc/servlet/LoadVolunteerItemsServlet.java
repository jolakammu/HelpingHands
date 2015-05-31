package edu.austincc.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import edu.austincc.db.AddressManager;
import edu.austincc.db.ElecCommuManager;
import edu.austincc.db.VolunteerItemsManager;
import edu.austincc.domain.Address;
import edu.austincc.domain.ElecCommu;
import edu.austincc.domain.VolunteerCSV;
import edu.austincc.domain.VolunteerItems;
import edu.austincc.utils.ReadVolunteerCSV;

/**
 * Servlet implementation class LoadVolunteerItems
 */
@WebServlet("/LoadVolunteerItemsServlet")
public class LoadVolunteerItemsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Resource(name="jdbc/DB")
	DataSource ds;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoadVolunteerItemsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url = "/WEB-INF/volunteerCSV.jsp";
		request.getRequestDispatcher(url).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url = "/WEB-INF/volunteerCSV.jsp";
		String filename = request.getParameter("filename") ;
		String action = request.getParameter("action") ;
		String delimiter = ",";
		ReadVolunteerCSV readVolunteerCSV = new ReadVolunteerCSV();
		ArrayList<VolunteerCSV> volunteerItemsCSV = readVolunteerCSV.readVolunteerItems(filename, delimiter);
		
		for (VolunteerCSV volunteerCSV : volunteerItemsCSV) {
			Address address = new Address(0, volunteerCSV.getOrgDelivery(), volunteerCSV.getOrgCity(), volunteerCSV.getOrgState(), volunteerCSV.getOrgCountry(), volunteerCSV.getOrgzip());
			ElecCommu  elecCommu = new ElecCommu(0,"PHONE",volunteerCSV.getOrgPhone());
			
			//Check for existence of address. if not create the address
			int addressId = new AddressManager(ds).getAddress(address);			
			if (addressId == 0) {
				addressId = new AddressManager(ds).addAddress(address);
			}
			//Check for existence of phone. if not create the phone
			int elecCommuId = new ElecCommuManager(ds).getElecCommu(elecCommu);			
			if (elecCommuId == 0) {
				elecCommuId = new ElecCommuManager(ds).addElecCommu(elecCommu);
			}		
			//Date workDate = new java.sql.Date(volunteerCSV.getWorkBeginDtTime());
			
			// Create the Volunteering Opportunities
			
			DateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
			Date workDate;
			try {
				workDate = formatter.parse(volunteerCSV.getWorkBeginDtTime());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				workDate = null;
				e.printStackTrace();
			};
			VolunteerItems volunteerItems = new VolunteerItems(0, volunteerCSV.getOrgName(), volunteerCSV.getOrgCategory(), volunteerCSV.getWorkDesc(), Integer.parseInt(volunteerCSV.getManHrs()), workDate,addressId,elecCommuId);
			
			int volunteertemId = new VolunteerItemsManager(ds).addVolunteerItems(volunteerItems);			
		}		
		
		response.sendRedirect(request.getContextPath() + url);
		
	}

}
