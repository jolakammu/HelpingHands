package edu.austincc.utils;

import java.util.Date;

public class DateYearMonth implements Comparable<DateYearMonth> {

	int monthNum;
	int yearNum;
	String monthName;

	public DateYearMonth(int monthNum, int yearNum, String monthName) {
		super();
		this.monthNum = monthNum;
		this.yearNum = yearNum;
		this.monthName = monthName;
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

	@Override
	public String toString() {
		return "DateYearMonth [monthNum=" + monthNum + ", yearNum=" + yearNum
				+ ", monthName=" + monthName + "]";
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
