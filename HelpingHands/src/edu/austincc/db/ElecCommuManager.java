package edu.austincc.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import edu.austincc.domain.ElecCommu;

public class ElecCommuManager {
	
	DataSource ds;

	public ElecCommuManager(DataSource ds) {
		this.ds = ds;
	}

	// Electronic Communication
	public int addElecCommu(ElecCommu elecCommu) {

		PreparedStatement ps = null;
		ResultSet rs = null;
		int elecCommuId = 0;
		try {
				Connection connection;
				connection = ds.getConnection();
				ps = connection.prepareStatement("select Max(ELEC_COMMU_ID) + 1 as ELEC_COMMU_ID from app.HH_ELEC_COMMU");
			
				ResultSet resultSet = ps.executeQuery();
				while (resultSet.next()) {
					elecCommuId = Integer.parseInt(resultSet.getString("ELEC_COMMU_ID"));
				}
				ps = connection.prepareStatement("Insert into app.HH_ELEC_COMMU(ELEC_COMMU_ID, ELEC_COMMU_TYP,ELEC_COMMU_NUM) values (?,?,?)");
				ps.setInt(1, elecCommuId);
				ps.setString(2, elecCommu.getElecCommuTyp());	
				ps.setInt(3, elecCommu.getElecCommuNum());
			
			ps.executeUpdate();
			ps.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
    	return elecCommuId;
	}	

	

	public void updateElecCommu(ElecCommu elecCommu) {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		try {
				Connection connection;
				connection = ds.getConnection();
				ps = connection.prepareStatement("Update app.HH_ELEC_COMMU set ELEC_COMMU_TYP=?, ELEC_COMMU_NUM=? where ELEC_COMMU_ID =?");
				ps.setInt(1, elecCommu.getElecCommuId());
				ps.setString(2, elecCommu.getElecCommuTyp());	
				ps.setInt(3, elecCommu.getElecCommuNum());
			
			ps.executeUpdate();
			ps.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}	

}
