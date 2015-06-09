package edu.austincc.servlet;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import edu.austincc.db.VolunteerSchitemsManager;

/**
 * Servlet implementation class DeleteVolunteeringSchItem
 */
@WebServlet("/DeleteVolunteeringSchItem")
public class DeleteVolunteeringSchItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(name="jdbc/DB")
	DataSource ds;
          
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteVolunteeringSchItem() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String volunteerSchItemIdStr = request.getParameter("volunteerSchItemId");
		if (volunteerSchItemIdStr != null) {
			int volunteerSchItemId = Integer.parseInt(volunteerSchItemIdStr);
			VolunteerSchitemsManager vsim = new VolunteerSchitemsManager(ds);
			vsim.deleteVolunteeringItemSch(volunteerSchItemId);
			response.sendRedirect("VolunteeringSchListServlet");
		} else {
			String volunteertemIdStr = request.getParameter("volunteertemId");
			if (volunteertemIdStr != null) {
				int volunteertemId = Integer.parseInt(volunteertemIdStr);
				VolunteerSchitemsManager vsim = new VolunteerSchitemsManager(ds);
				//vsim.deleteVolunteeringItemSch(volunteerSchItemId);
				response.sendRedirect("VolunteeringListServlet");
		}
	}
  
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}
}
