package edu.austincc.servlet;

import java.io.IOException;
import java.util.Enumeration;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import edu.austincc.db.VolunteerSchitemsManager;

/**
 * Servlet implementation class CreateVolunteeringItemSch
 */
@WebServlet("/CreateVolunteeringItemSch")
public class CreateVolunteeringItemSch extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource(name="jdbc/DB")
	DataSource ds;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateVolunteeringItemSch() {
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
		HttpSession session = request.getSession();
		int userId = (int) session.getAttribute("userId");
		
		Enumeration<String> viIdKeys = request.getParameterNames();
		int volunteeringItemId = 0;
		int newSignedHrs = 0;
		int filledHrs = 0;
		String selectedVolunteeringItemId;
		int value , lastvalue = 0;
		   while (viIdKeys.hasMoreElements() )
		   {
			   selectedVolunteeringItemId =  (String) viIdKeys.nextElement();
			   try {
				volunteeringItemId  = Integer.parseInt(selectedVolunteeringItemId);
			} catch (NumberFormatException e) {
				volunteeringItemId = 0;	
			}
			
			if (volunteeringItemId != 0) {
				newSignedHrs = Integer.parseInt(request.getParameter(selectedVolunteeringItemId));				
				// Get current filled hrs
				filledHrs = new VolunteerSchitemsManager(ds).getFilledHrs(userId, volunteeringItemId);
				
				if (newSignedHrs > 0) {
					if (filledHrs == 0) {
						//Create Volunteering Scheduled Item
						int volunteerSchItemId = new VolunteerSchitemsManager(ds).addVolunteeringItemSch(userId, volunteeringItemId, newSignedHrs);
					} else {
						//Update Volunteering Scheduled Item
						int volunteerSchItemId =   new VolunteerSchitemsManager(ds).getVolunteerSchItemId(userId, volunteeringItemId);
						volunteerSchItemId = new VolunteerSchitemsManager(ds).updateVolunteeringItemSch(volunteerSchItemId, newSignedHrs);
					}
				
				}

			}
		     
				
		   }
		   response.sendRedirect("VolunteeringSchListServlet");

	}

}
