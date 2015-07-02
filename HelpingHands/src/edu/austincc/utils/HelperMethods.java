package edu.austincc.utils;

import java.text.DateFormatSymbols;

public class HelperMethods {

	public String getMonthString(int monthNum) {

		return new DateFormatSymbols().getMonths()[monthNum - 1];
	}
}
