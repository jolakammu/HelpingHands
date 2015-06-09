package edu.austincc.utils;

import java.util.Date;

public class DateYearMonth  implements Comparable<DateYearMonth>{
	
	int monthNum;
	int yearNum;
	String monthName;
	Date date;
	
	public DateYearMonth(int monthNum, int yearNum, String monthName, Date date) {
		super();
		this.monthNum = monthNum;
		this.yearNum = yearNum;
		this.monthName = monthName;
		this.date = date;
	}
	
	public int getMonthNum() {
		return monthNum;
	}
	public void setMonthNum(int monthNum) {
		this.monthNum = monthNum;
	}
	public int getYearNum() {
		return yearNum;
	}
	public void setYearNum(int yearNum) {
		this.yearNum = yearNum;
	}
	public String getMonthName() {
		return monthName;
	}
	public void setMonthName(String monthName) {
		this.monthName = monthName;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "DateYearMonth [monthNum=" + monthNum + ", yearNum=" + yearNum
				+ ", monthName=" + monthName + ", date=" + date + "]";
	}

	@Override
	public int compareTo(DateYearMonth other) {
		
		if (this.getYearNum() > other.yearNum) {
			return 1;
		} else if (this.getYearNum() < other.yearNum) {
			return -1;
		} else {
			if (this.getMonthNum() > other.monthNum) {
				return 1;
			} else if (this.getMonthNum() < other.monthNum) {
				return 1;
			} else {
				return 0;
			}
		}
			
	}

	

}
