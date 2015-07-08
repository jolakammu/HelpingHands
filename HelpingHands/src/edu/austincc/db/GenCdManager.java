package edu.austincc.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import edu.austincc.domain.GenCodes;

public class GenCdManager {

	DataSource ds;

	public GenCdManager(DataSource ds) {
		this.ds = ds;
	}

	public ArrayList<GenCodes> getGenCodes(String genCdTyp) {
		ArrayList<GenCodes> genCodes = new ArrayList<GenCodes>();
		Connection connection = null;
		ResultSet rs = null;
		PreparedStatement ps = null;

		try {
			connection = ds.getConnection();
			ps = connection
					.prepareStatement("select distinct GEN_CD_TXT, GEN_CD_NAME from HH_GEN_CD cd, HH_GEN_CD_TYP cdtyp where cdtyp.GEN_CD_TYP_ID = cd.GEN_CD_TYP_ID and cdtyp.GEN_CD_TYP_NAME = ? order by GEN_CD_NAME asc");
			ps.setString(1, genCdTyp);
			rs = ps.executeQuery();

			while (rs.next()) {

				genCodes.add(new GenCodes(rs.getString("GEN_CD_TXT"),
						rs.getString("GEN_CD_NAME")));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps, rs, connection);;
		}
		return genCodes;
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
