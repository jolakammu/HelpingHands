package edu.austincc.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import edu.austincc.domain.ElecctronicCommunication;

public class ElecCommuManager {

	DataSource ds;

	public ElecCommuManager(DataSource ds) {
		this.ds = ds;
	}

	public int getElecCommu(ElecctronicCommunication elecCommu) {

		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection connection = null;
		int elecCommuId = 0;
		try {

			connection = ds.getConnection();
			ps = connection
					.prepareStatement("select Coalesce(ELEC_COMMU_ID,0) as ELEC_COMMU_ID from app.HH_ELEC_COMMU where ELEC_COMMU_NUM = ? and ELEC_COMMU_TYP = ?");
			ps.setString(1, elecCommu.getElecCommuTyp());
			ps.setString(2, elecCommu.getElecCommuNum());
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				elecCommuId = resultSet.getInt("ELEC_COMMU_ID");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps, rs, connection);;
		}

		return elecCommuId;
	}

	// Electronic Communication
	public int addElecCommu(ElecctronicCommunication elecCommu) {

		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection connection = null;
		int elecCommuId = 0;
		try {
			connection = ds.getConnection();
			ps = connection
					.prepareStatement("select Coalesce(Max(ELEC_COMMU_ID),0) + 1  as ELEC_COMMU_ID from app.HH_ELEC_COMMU");

			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				elecCommuId = resultSet.getInt("ELEC_COMMU_ID");
			}
			ps = connection
					.prepareStatement("Insert into app.HH_ELEC_COMMU(ELEC_COMMU_ID, ELEC_COMMU_TYP,ELEC_COMMU_NUM) values (?,?,?)");
			ps.setInt(1, elecCommuId);
			ps.setString(2, elecCommu.getElecCommuTyp());
			ps.setString(3, elecCommu.getElecCommuNum());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps, rs, connection);;
		}

		return elecCommuId;
	}

	public void updateElecCommu(ElecctronicCommunication elecCommu) {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		Connection connection = null;
		try {
			connection = ds.getConnection();
			ps = connection
					.prepareStatement("Update app.HH_ELEC_COMMU set ELEC_COMMU_TYP=?, ELEC_COMMU_NUM=? where ELEC_COMMU_ID =?");
			ps.setInt(1, elecCommu.getElecCommuId());
			ps.setString(2, elecCommu.getElecCommuTyp());
			ps.setString(3, elecCommu.getElecCommuNum());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps, null, connection);;
		}

	}
	private void close(PreparedStatement ps, ResultSet rs, Connection connection) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}
