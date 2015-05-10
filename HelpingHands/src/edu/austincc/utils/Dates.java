package edu.austincc.utils;

import java.util.Calendar;
import java.util.Date;

public class Dates {
	
	public Date addYear(int year) {
		
		Calendar calender = Calendar.getInstance();
		Date newDate = new Date();
		calender.setTime(newDate);
		calender.add(Calendar.YEAR, year); // Where n is int
		newDate = calender.getTime();
		System.out.println(newDate);
		return newDate;
	}

}
