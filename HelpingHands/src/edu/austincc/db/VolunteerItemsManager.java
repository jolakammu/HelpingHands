package edu.austincc.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import edu.austincc.domain.Address;
import edu.austincc.domain.ElecCommu;
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
				ps = connection.prepareStatement("select Coalesce(Max(VOLUNTEER_ITEM_ID),0) + 1 as VOLUNTEER_ITEM_ID from app.HH_VOLUNTEER_ITEMS");

			
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
				ps.setDate(6, new java.sql.Date(volunteerItems.getWorkBeginDtTime().getTime()));
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

	
	public ArrayList<VolunteerItems> listVolunteerItems() {
		
		ArrayList<VolunteerItems> vilArray = new ArrayList<VolunteerItems>();
		 
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			Connection connection;
			connection = ds.getConnection();
			ps = connection.prepareStatement("Select vi.VOLUNTEER_ITEM_ID, vi.ORG_NAME, vi.ORG_CATEGORY, vi.WORK_DESC, vi.MAN_HRS, vi.WORK_BEGIN_DT, vi.ADDRESS_ID, addr.DELIVERY_TXT, addr.city, addr.state_cd, addr.COUNTRY_CD, addr.ZIP_TXT, vi.ELEC_COMMU_ID, ec.ELEC_COMMU_TYP, ec.ELEC_COMMU_NUM from app.HH_VOLUNTEER_ITEMS vi, APP.HH_ADDRESS addr, APP.HH_ELEC_COMMU ec where vi.ADDRESS_ID = addr.ADDRESS_ID and vi.ELEC_COMMU_ID = ec.ELEC_COMMU_ID");
			
			rs = ps.executeQuery();
			while (rs.next()) {
				Address address = new Address(Integer.parseInt(rs.getString("ADDRESS_ID")),rs.getString("DELIVERY_TXT"),rs.getString("city"),rs.getString("state_cd"),rs.getString("COUNTRY_CD"),rs.getString("ZIP_TXT"));
				ElecCommu elecCommu = new ElecCommu(Integer.parseInt(rs.getString("ELEC_COMMU_ID")), rs.getString("ELEC_COMMU_TYP"),rs.getString("ELEC_COMMU_NUM"));			
				vilArray.add(new VolunteerItems(Integer.parseInt(rs.getString("VOLUNTEER_ITEM_ID")),rs.getString("ORG_NAME"),rs.getString("ORG_CATEGORY"),rs.getString("WORK_DESC"),Integer.parseInt(rs.getString("MAN_HRS")),
							rs.getDate("WORK_BEGIN_DT"),Integer.parseInt(rs.getString("ADDRESS_ID")),Integer.parseInt(rs.getString("ELEC_COMMU_ID")),address,elecCommu));
			}	
			ps.close();
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return vilArray;
	
	}

}
