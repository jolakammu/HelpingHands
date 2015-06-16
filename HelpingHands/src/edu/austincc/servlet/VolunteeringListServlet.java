package edu.austincc.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import edu.austincc.db.VolunteerItemsManager;
import edu.austincc.db.VolunteerSchitemsManager;
import edu.austincc.domain.VolunteerItems;
import edu.austincc.utils.DateYearMonth;

/**
 * Servlet implementation class VolunteeringListServlet
 */
@WebServlet({ "/VolunteeringListServlet", "/VolunteeringList" })
public class VolunteeringListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource(name="jdbc/DB")
	DataSource ds;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VolunteeringListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String url = "/WEB-INF/volunteeringList.jsp";
		
		ArrayList<VolunteerItems> vilArray = new VolunteerItemsManager(ds).listVolunteerItems();
		
		Map<DateYearMonth,List<VolunteerItems>> vilMap = new TreeMap();
		
		HashMap<Integer, Integer> filledHrsMap = new HashMap<Integer,Integer>();
			
		if (!(vilArray==null)) {		
			DateFormat yDate = new SimpleDateFormat("yyy");
			DateFormat mDate = new SimpleDateFormat("MM");
			DateFormat MonthName = new SimpleDateFormat("MMMM");
			String lastyearMonth = null;
			String yearMonth     = null;		
			List<VolunteerItems> vilList = null;
			VolunteerItems dupVolunteerItems = null;
			DateYearMonth dateYearMonth = null;
			for (VolunteerItems volunteerItems : vilArray) {
				dupVolunteerItems = volunteerItems;
				yearMonth = mDate.format(volunteerItems.getWorkBeginDtTime()) + ' ' + yDate.format(volunteerItems.getWorkBeginDtTime()) ;
				dateYearMonth = new DateYearMonth((int) Integer.parseInt(mDate.format(volunteerItems.getWorkBeginDtTime())),(int) Integer.parseInt(yDate.format(volunteerItems.getWorkBeginDtTime())),MonthName.format(volunteerItems.getWorkBeginDtTime()),volunteerItems.getWorkBeginDtTime());
				
				if (yearMonth.equals(lastyearMonth) || lastyearMonth == null) {
					if (lastyearMonth == null) {
						vilList = new ArrayList<VolunteerItems>();
					}
					vilList.add(volunteerItems);
					filledHrsMap.put(volunteerItems.getVolunteertemId(), new VolunteerSchitemsManager(ds).getFilledHrs(volunteerItems.getVolunteertemId()));
				} else {
					if (!(lastyearMonth == null)) {
						vilMap.put(dateYearMonth, vilList);
						vilList = new ArrayList<VolunteerItems>();
						vilList.add(volunteerItems);
						filledHrsMap.put(volunteerItems.getVolunteertemId(), new VolunteerSchitemsManager(ds).getFilledHrs(volunteerItems.getVolunteertemId()));
					} 				
				}	
				lastyearMonth = yearMonth;			
			}			
			//vilList.add(dupVolunteerItems);
			if (vilList != null) {
				vilMap.put(dateYearMonth, vilList);	
			}
			
		}	
		request.setAttribute("vilMap", vilMap);
		request.setAttribute("filledHrsMap", filledHrsMap);
		getServletContext().getRequestDispatcher(url).forward(request, response);
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
