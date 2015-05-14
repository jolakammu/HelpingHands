package edu.austincc.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import edu.austincc.domain.VolunteerCSV;
import edu.austincc.domain.VolunteerItems;

public class VolunteerItemsManager {
	
	DataSource ds;

	public VolunteerItemsManager(DataSource ds) {
		this.ds = ds;
	}

	public int addVolunteerItems(VolunteerItems volunteerItems) {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		ResultSet rs = null;
		Integer volunteertemId = 0;
		try {
				Connection connection;
				connection = ds.getConnection();
				ps = connection.prepareStatement("select Coalesce(Max(VOLUNTEER_ITEM_ID),0) + 1 as VOLUNTEER_ITEM_ID from app.HH_ADDRESS");

			
				ResultSet resultSet = ps.executeQuery();
				while (resultSet.next()) {
					volunteertemId = Integer.parseInt(resultSet.getString("VOLUNTEER_ITEM_ID"));
					
				}
				ps = connection.prepareStatement("Insert into app.HH_VOLUNTEER_ITEMS(VOLUNTEER_ITEM_ID, ORG_NAME,ORG_CATEGORY,WORK_DESC,MAN_HRS,WORK_BEGIN_DT,ADDRESS_ID,ELEC_COMMU_ID) values (?,?,?,?,?,?,?,?)");
				ps.setInt(1, volunteertemId);
				ps.setString(2, volunteerItems.getOrgName());	
				ps.setString(3, volunteerItems.getOrgCategory());
				ps.setString(4, volunteerItems.getWorkDesc());
				ps.setLong(5,volunteerItems.getManHrs());
				ps.setDate(6,(java.sql.Date) volunteerItems.getWorkBeginDtTime());
				ps.setInt(7, volunteerItems.getAddressId());
				ps.setInt(8, volunteerItems.getElecCommuId());
				
			
			ps.executeUpdate();
			ps.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
    	return volunteertemId;
	}	


}
