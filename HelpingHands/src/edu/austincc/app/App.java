package edu.austincc.app;

import java.util.Calendar;
import java.util.Date;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Date date = new Date();
		
		//System.out.println("date = " + date);

		
		Calendar cal = Calendar.getInstance();
		
		
		cal.add(Calendar.YEAR,3);
		date =cal.getTime();
		
		System.out.println("cal = " + cal);
		
		System.out.println("date = " + date);
	}

}
