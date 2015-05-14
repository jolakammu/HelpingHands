package edu.austincc.servlet;

import java.io.IOException;
import java.util.ArrayList;
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
import edu.austincc.domain.Address;
import edu.austincc.domain.ElecCommu;
import edu.austincc.domain.VolunteerCSV;
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String filename = request.getParameter("filename") ;
		String action = request.getParameter("action") ;
		String delimiter = ",";
		ReadVolunteerCSV readVolunteerCSV = new ReadVolunteerCSV();
		ArrayList<VolunteerCSV> volunteerItems = readVolunteerCSV.readVolunteerItems(filename, delimiter);
		
		for (VolunteerCSV volunteerItem : volunteerItems) {
			Address address = new Address(0, volunteerItem.getOrgDelivery(), volunteerItem.getOrgCity(), volunteerItem.getOrgState(), volunteerItem.getOrgCountry(), volunteerItem.getOrgzip());
			ElecCommu  elecCommu = new ElecCommu(0,"PHONE",volunteerItem.getOrgPhone());
			
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

			
			// Create the Volunteering Opertunities
		}		
	}

}
