package edu.austincc.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

import edu.austincc.domain.VolunteerCSV;
import edu.austincc.domain.VolunteerItems;

public class ReadVolunteerCSV {
	
	public ArrayList<VolunteerCSV> readVolunteerItems (InputStream fileasInsputStream, String delimiter) {
		
		BufferedReader bufferedReader = null;
		String data = "";
		ArrayList<VolunteerCSV> volunteerItems = new ArrayList<VolunteerCSV>(); 
	 
		try {
	 
			bufferedReader = new BufferedReader(new InputStreamReader(fileasInsputStream));
			while ((data = bufferedReader.readLine()) != null) {
	 
			        // use comma as separator
				String[] stringData = data.split(delimiter);
				VolunteerCSV volunteerCSV = new VolunteerCSV(stringData[0],stringData[1],stringData[2],stringData[3],stringData[4],stringData[5],stringData[6],stringData[7],stringData[8],stringData[9],stringData[10]);
				volunteerItems.add(volunteerCSV);
			}
	 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
	
			
		}	
		return volunteerItems;
	}
}
