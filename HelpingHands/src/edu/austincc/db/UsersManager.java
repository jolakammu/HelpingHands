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

		try {
			Connection connection;
			connection = ds.getConnection();

			PreparedStatement ps = connection
					.prepareStatement("select USER_ID,EMAIL_TXT,NAME,PASSWORD_EXPIRY_DT,ROLE_CD, USER_TYP from SE_USER where EMAIL_TXT = ? and PASSWORD_TXT = ?");
			ps.setString(1, email);
			ps.setString(2, password);
			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {

				validatedUser = new User(resultSet.getInt("USER_ID"),
						resultSet.getString("EMAIL_TXT"),
						resultSet.getString("NAME"),
						resultSet.getString("ROLE_CD"),
						resultSet.getString("USER_TYP")

				);
			}

			resultSet.close();
			ps.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return validatedUser;
	}

	public User getUser(String email) {

		User validateUser = null;

		try {
			Connection connection;
			connection = ds.getConnection();

			PreparedStatement ps = connection
					.prepareStatement("select Distinct USER_ID,EMAIL_TXT,NAME,ROLE_CD,USER_TYP  from SE_USER where EMAIL_TXT = ?");
			ps.setString(1, email);
			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {

				validateUser = new User(Integer.parseInt(resultSet
						.getString("USER_ID")),
						resultSet.getString("EMAIL_TXT"),
						resultSet.getString("NAME"),
						resultSet.getString("ROLE_CD"),
						resultSet.getString("USER_TYP")

				);
			}

			resultSet.close();
			ps.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return validateUser;
	}

	public int addUser(User user) {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		ResultSet rs = null;
		int userId = 0;
		try {
			Connection connection;
			connection = ds.getConnection();
			ps = connection
					.prepareStatement("select Coalesce(Max(USER_ID),0) + 1  as USER_ID from app.SE_USER");

			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				userId = Integer.parseInt(resultSet.getString("USER_ID"));
			}

			ps = connection
					.prepareStatement("insert into app.SE_USER(USER_ID,EMAIL_TXT,NAME,PASSWORD_TXT,PASSWORD_EXPIRY_DT,ROLE_CD,USER_TYP,ADDRESS_ID,ELEC_COMMU_ID) values (?,?,?,?,?,?,?,?,?)");
			ps.setInt(1, userId);
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getName());
			ps.setString(4, user.getPassword());
			ps.setDate(5, new java.sql.Date(user.getPasswordExpiry().getTime()));
			ps.setString(6, user.getRole());
			ps.setString(7, user.getType());
			ps.setInt(8, user.getAddressId());
			ps.setInt(9, user.getElecCommuId());

			ps.executeUpdate();
			ps.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return userId;
	}

	public void updateUser(User user) {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Connection connection;
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
			ps.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}