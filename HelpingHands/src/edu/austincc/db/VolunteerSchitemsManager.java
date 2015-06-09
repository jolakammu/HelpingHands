package edu.austincc.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import edu.austincc.domain.Address;
import edu.austincc.domain.ElecCommu;
import edu.austincc.domain.User;
import edu.austincc.domain.VolunteerItems;
import edu.austincc.domain.VolunteerSchItem;

public class VolunteerSchitemsManager {
	
	DataSource ds;

	public VolunteerSchitemsManager(DataSource ds) {
		this.ds = ds;
	}
	

	public int getFilledHrs(int volunteerItemId) {
		int filledHrs = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection connection;		
		
		try {
			connection = ds.getConnection();
			ps = connection.prepareStatement("select Coalesce(sum(SIGNED_MAN_HRS),0) as SIGNED_MAN_HRS from app.HH_VOLUNTEER_SCH_ITEMS where VOLUNTEER_ITEM_ID = ?");
			ps.setInt(1, volunteerItemId);
			rs = ps.executeQuery();
			while (rs.next()) {
				filledHrs = Integer.parseInt(rs.getString("SIGNED_MAN_HRS"));
				
			}
			rs.close();
			ps.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return filledHrs;
	}

	public int getFilledHrs(int userId, int volunteerItemId) {
		int filledHrs = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection connection;		
		
		try {
			connection = ds.getConnection();
			ps = connection.prepareStatement("select Coalesce(sum(SIGNED_MAN_HRS),0) as SIGNED_MAN_HRS from app.HH_VOLUNTEER_SCH_ITEMS where User_id = ?  and VOLUNTEER_ITEM_ID = ?");
			ps.setInt(1, userId);
			ps.setInt(2, volunteerItemId);
			rs = ps.executeQuery();
			while (rs.next()) {
				filledHrs = Integer.parseInt(rs.getString("SIGNED_MAN_HRS"));
				
			}
			rs.close();
			ps.close();
			connection.close();	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return filledHrs;
	}

	public int getVolunteerSchItemId(int userId, int volunteerItemId) {
		int volunteerSchItemId = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection connection;		
		
		try {
			connection = ds.getConnection();
			ps = connection.prepareStatement("select Coalesce(VOLUNTEER_SCH_ITEM_ID,0) as VOLUNTEER_SCH_ITEM_ID from app.HH_VOLUNTEER_SCH_ITEMS where User_id = ?  and VOLUNTEER_ITEM_ID = ?");
			ps.setInt(1, userId);
			ps.setInt(2, volunteerItemId);
			rs = ps.executeQuery();
			while (rs.next()) {
				volunteerSchItemId = Integer.parseInt(rs.getString("VOLUNTEER_SCH_ITEM_ID"));
				
			}
			rs.close();
			ps.close();
			connection.close();	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return volunteerSchItemId;
	}
	
	public int addVolunteeringItemSch (int userId, int volunteerItemId, int signedHrs) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		int volunteerSchItemId = 0;
		
		try {
			Connection connection;
			connection = ds.getConnection();
			ps = connection.prepareStatement("select Coalesce(Max(VOLUNTEER_SCH_ITEM_ID),0) + 1  VOLUNTEER_SCH_ITEM_ID from app.HH_VOLUNTEER_SCH_ITEMS");
			
			rs = ps.executeQuery();
			while (rs.next()) {
				volunteerSchItemId = Integer.parseInt(rs.getString("VOLUNTEER_SCH_ITEM_ID"));
			}

			ps = connection.prepareStatement("Insert into APP.HH_VOLUNTEER_SCH_ITEMS (VOLUNTEER_SCH_ITEM_ID,USER_ID ,VOLUNTEER_ITEM_ID,SIGNED_MAN_HRS) values (?,?,?,?)");
			ps.setInt(1, volunteerSchItemId);
			ps.setInt(2, userId);
			ps.setInt(3, volunteerItemId);
			ps.setInt(4, signedHrs);
			ps.executeUpdate();
			ps.close();
			rs.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return volunteerSchItemId;
	}
	

	public int updateVolunteeringItemSch (int volunteerSchItemId, int signedHrs) {
		PreparedStatement ps = null;
				
		try {
			Connection connection;
			connection = ds.getConnection();

			ps = connection.prepareStatement("Update  APP.HH_VOLUNTEER_SCH_ITEMS set SIGNED_MAN_HRS = Coalesce(SIGNED_MAN_HRS,0) + ? where VOLUNTEER_SCH_ITEM_ID = ?");
			ps.setInt(1, volunteerSchItemId);
			ps.setInt(2, signedHrs);
			ps.executeUpdate();
			ps.close();
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return volunteerSchItemId;
	}

	public void deleteVolunteeringItemSch (int volunteerSchItemId) {
		PreparedStatement ps = null;
				
		try {
			Connection connection;
			connection = ds.getConnection();

			ps = connection.prepareStatement("Delete from  app.HH_VOLUNTEER_SCH_ITEMS where VOLUNTEER_SCH_ITEM_ID = ?");
			ps.setInt(1, volunteerSchItemId);
			ps.executeUpdate();
			ps.close();
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public ArrayList<VolunteerSchItem> listVolunteerSchItems(int userId) {
		
		ArrayList<VolunteerSchItem> vsilArray = new ArrayList<VolunteerSchItem>();
		 
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			Connection connection;
			connection = ds.getConnection();
			ps = connection.prepareStatement("Select vi.VOLUNTEER_ITEM_ID, vi.ORG_NAME, vi.ORG_CATEGORY, vi.WORK_DESC, vi.MAN_HRS, vi.WORK_BEGIN_DT, vi.ADDRESS_ID, addr.DELIVERY_TXT, addr.city, addr.state_cd, addr.COUNTRY_CD, addr.ZIP_TXT, vi.ELEC_COMMU_ID, ec.ELEC_COMMU_TYP, ec.ELEC_COMMU_NUM, vsi.VOLUNTEER_SCH_ITEM_ID,vsi.USER_ID,vsi.VOLUNTEER_ITEM_ID,vsi.SIGNED_MAN_HRS from app.HH_VOLUNTEER_ITEMS vi, APP.HH_ADDRESS addr, APP.HH_ELEC_COMMU ec , APP.HH_VOLUNTEER_SCH_ITEMS vsi where vi.ADDRESS_ID = addr.ADDRESS_ID  and vi.ELEC_COMMU_ID = ec.ELEC_COMMU_ID and vi.VOLUNTEER_ITEM_ID = vsi.VOLUNTEER_ITEM_ID and vsi.user_id = ? order by vi.WORK_BEGIN_DT asc");
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			while (rs.next()) {
				Address address = new Address(Integer.parseInt(rs.getString("ADDRESS_ID")),rs.getString("DELIVERY_TXT"),rs.getString("city"),rs.getString("state_cd"),rs.getString("COUNTRY_CD"),rs.getString("ZIP_TXT"));
				ElecCommu elecCommu = new ElecCommu(Integer.parseInt(rs.getString("ELEC_COMMU_ID")), rs.getString("ELEC_COMMU_TYP"),rs.getString("ELEC_COMMU_NUM"));
				User user = new User (Integer.parseInt(rs.getString("USER_ID")));
				VolunteerItems volunteerItems = new VolunteerItems(Integer.parseInt(rs.getString("VOLUNTEER_ITEM_ID")), rs.getString("ORG_NAME"),rs.getString("ORG_CATEGORY"),rs.getString("WORK_DESC"),Integer.parseInt(rs.getString("MAN_HRS")),
						rs.getDate("WORK_BEGIN_DT"),Integer.parseInt(rs.getString("ADDRESS_ID")),Integer.parseInt(rs.getString("ELEC_COMMU_ID")),address,elecCommu);
				
				vsilArray.add(new VolunteerSchItem(Integer.parseInt(rs.getString("VOLUNTEER_SCH_ITEM_ID")), Integer.parseInt(rs.getString("SIGNED_MAN_HRS")),user, volunteerItems));
			}	
			ps.close();
			rs.close();
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return vsilArray;
	
	}


}
