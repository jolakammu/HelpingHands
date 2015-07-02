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
		try {
			Connection connection;
			connection = ds.getConnection();
			ResultSet resultSet = null;
			PreparedStatement ps = null;

			ps = connection
					.prepareStatement("select distinct GEN_CD_TXT, GEN_CD_NAME from HH_GEN_CD cd, HH_GEN_CD_TYP cdtyp where cdtyp.GEN_CD_TYP_ID = cd.GEN_CD_TYP_ID and cdtyp.GEN_CD_TYP_NAME = ? order by GEN_CD_NAME asc");
			ps.setString(1, genCdTyp);
			resultSet = ps.executeQuery();

			while (resultSet.next()) {

				genCodes.add(new GenCodes(resultSet.getString("GEN_CD_TXT"),
						resultSet.getString("GEN_CD_NAME")));

			}

			resultSet.close();
			ps.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return genCodes;
	}
}
