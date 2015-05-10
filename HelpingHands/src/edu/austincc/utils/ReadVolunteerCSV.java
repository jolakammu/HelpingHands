package edu.austincc.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import edu.austincc.domain.VolunteerItems;

public class ReadVolunteerCSV {
	
	public ArrayList<VolunteerItems> readVolunteerItems (String fileName, String delimiter) {
		
		BufferedReader bufferedReaderr = null;
		FileReader fileReader = null;
		String data = "";
		ArrayList<VolunteerItems> volunteerItemsArray = new ArrayList<VolunteerItems>(); 
	 
		try {
	 
			fileReader = new FileReader(fileName);
			bufferedReaderr = new BufferedReader(fileReader);
			while ((data = bufferedReaderr.readLine()) != null) {
	 
			        // use comma as separator
				String[] stringData = data.split(delimiter);
	 
			}
	 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bufferedReaderr != null) {
				try {
					bufferedReaderr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
	
			
		}	
		return volunteerItemsArray;
	}
}
