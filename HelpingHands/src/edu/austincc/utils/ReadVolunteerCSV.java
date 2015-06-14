package edu.austincc.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import edu.austincc.domain.VolunteerCSV;
import edu.austincc.domain.VolunteerItems;

public class ReadVolunteerCSV {
	
	public ArrayList<VolunteerCSV> readVolunteerItems (String fileName, String delimiter) {
		
		BufferedReader bufferedReader = null;
		FileReader fileReader = null;
		String data = "";
		ArrayList<VolunteerCSV> volunteerItems = new ArrayList<VolunteerCSV>(); 
	 
		try {
	 
			fileReader = new FileReader(fileName);
			bufferedReader = new BufferedReader(fileReader);
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
