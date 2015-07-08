package edu.austincc.db;

import java.rmi.ConnectException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.sql.DataSource;

import org.apache.derby.client.am.DateTime;

import edu.austincc.domain.User;

public class UsersManager {

	DataSource ds;

	public UsersManager(DataSource ds) {
		this.ds = ds;
	}

	public User getUser(String email, String password) {

		User validatedUser = null;
		PreparedStatement ps = null;
		Connection connection = null;
		ResultSet rs = null;
		try {
			connection = ds.getConnection();

			ps = connection
					.prepareStatement("select USER_ID,EMAIL_TXT,PASSWORD_TXT,SALT, NAME,PASSWORD_EXPIRY_DT,ROLE_CD, USER_TYP from SE_USER where EMAIL_TXT = ? and PASSWORD_TXT = ?");
			ps.setString(1, email);
			ps.setString(2, password);
			rs = ps.executeQuery();

			while (rs.next()) {

				validatedUser = new User(rs.getInt("USER_ID"),
						rs.getString("EMAIL_TXT"),
						rs.getString("NAME"),
						rs.getString("PASSWORD_TXT"),
						rs.getString("SALT"),					
						rs.getString("ROLE_CD"),
						rs.getString("USER_TYP")

				);
			}

			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps, rs, connection);;
		}
		return validatedUser;
	}

	public User getUser(String email) {

		User validateUser = null;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			connection = ds.getConnection();
			ps = connection
					.prepareStatement("select Distinct USER_ID,EMAIL_TXT,PASSWORD_TXT,SALT,NAME,ROLE_CD,USER_TYP  from SE_USER where EMAIL_TXT = ?");
			ps.setString(1, email);
			rs = ps.executeQuery();

			while (rs.next()) {

				validateUser = new User(Integer.parseInt(rs
						.getString("USER_ID")),
						rs.getString("EMAIL_TXT"),
						rs.getString("NAME"),
						rs.getString("PASSWORD_TXT"),
						rs.getString("SALT"),
						rs.getString("ROLE_CD"),
						rs.getString("USER_TYP")

				);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps, rs, connection);;
		}

		return validateUser;
	}

	public int addUser(User user) {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection connection = null;
		int userId = 0;
		try {
			connection = ds.getConnection();
			ps = connection
					.prepareStatement("select Coalesce(Max(USER_ID),0) + 1  as USER_ID from app.SE_USER");

			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				userId = Integer.parseInt(resultSet.getString("USER_ID"));
			}

			ps = connection
					.prepareStatement("insert into app.SE_USER(USER_ID,EMAIL_TXT,NAME,PASSWORD_TXT,SALT,PASSWORD_EXPIRY_DT,ROLE_CD,USER_TYP,ADDRESS_ID,ELEC_COMMU_ID) values (?,?,?,?,?,?,?,?,?,?)");
			ps.setInt(1, userId);
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getName());
			ps.setString(4, user.getPassword());
			ps.setString(5, user.getSalt());
			ps.setDate(6, new java.sql.Date(user.getPasswordExpiry().getTime()));
			ps.setString(7, user.getRole());
			ps.setString(8, user.getType());
			ps.setInt(9, user.getAddressId());
			ps.setInt(10, user.getElecCommuId());

			ps.executeUpdate();


		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps, null, connection);;
		}

		return userId;
	}

	public void updateUser(User user) {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection connection = null;
		try {
			
			connection = ds.getConnection();
			Date date = new java.sql.Date(3000 - 12 - 31);

			ps = connection
					.prepareStatement("Update app.SE_USER set EMAIL_TXT= ?, NAME = ?, PASSWORD_TXT=?, PASSWORD_EXPIRY_DT=?,ROLE_CD=?, user_typ=?,ADDRESS_ID=?,ELEC_COMMU_ID=? where USER_ID = ?");
			ps.setString(1, user.getEmail());
			ps.setString(2, user.getName());
			ps.setString(3, user.getPassword());
			ps.setDate(4, (java.sql.Date) date);
			ps.setString(5, user.getRole());
			ps.setString(6, user.getType());
			ps.setInt(7, user.getAddressId());
			ps.setInt(8, user.getElecCommuId());
			ps.setInt(9, user.getUserId());
			
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