package edu.austincc.db;

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
		
		User validateUser = null;

		try {
			Connection connection;
			connection = ds.getConnection();

			PreparedStatement ps = connection.prepareStatement("select USER_ID,EMAIL_TXT,NAME,PASSWORD_EXPIRY_DT,ROLE_CD from SE_USER where EMAIL_TXT = ? and PASSWORD_TXT = ?");
			ps.setString(1, email);
			ps.setString(2, password);
			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {
				//DateFormat df = new SimpleDateFormat("MM/DD/YYYY");
				//String dateString = resultSet.getString("PASSWORD_EXPIRY_DT");
				//Date passwordExpiryDate = df.parse(dateString);
				
				validateUser = new User( Integer.parseInt(resultSet.getString("USER_ID")),
									resultSet.getString("EMAIL_TXT"),
									resultSet.getString("NAME"),
									resultSet.getString("ROLE_CD")
									
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
	
	public User getUser(String email) {
		
		User validateUser = null;

		try {
			Connection connection;
			connection = ds.getConnection();

			PreparedStatement ps = connection.prepareStatement("select Distinct USER_ID,EMAIL_TXT,NAME from SE_USER where EMAIL_TXT = ?");
			ps.setString(1, email);
			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {
				//DateFormat df = new SimpleDateFormat("MM/DD/YYYY");
				//String dateString = resultSet.getString("PASSWORD_EXPIRY_DT");
				//Date passwordExpiryDate = df.parse(dateString);
				
				validateUser = new User( Integer.parseInt(resultSet.getString("USER_ID")),
									resultSet.getString("EMAIL_TXT"),
									resultSet.getString("NAME"),
									resultSet.getString("ROLE_CD")
									
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
			ps = connection.prepareStatement("select Max(USER_ID) + 1 as USER_ID from app.SE_USER");
			Date date = new java.sql.Date(3000-12-31);
			
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				userId = Integer.parseInt(resultSet.getString("USER_ID"));
			}
			
			ps = connection.prepareStatement("insert into app.SE_USER(USER_ID,EMAIL_TXT,NAME,PASSWORD_TXT,PASSWORD_EXPIRY_DT,ROLE_CD,ADDRESS_ID,ELEC_COMMMU_ID) values (?,?,?,?,?,?,?,?)");
			ps.setInt(1, userId);
			ps.setString(2, user.getEmail());	
			ps.setString(3, user.getName());
			ps.setString(4, user.getPassword());
			ps.setDate(5,(java.sql.Date) date);
			ps.setString(6, user.getRole());
			ps.setInt(7, user.getAddressId());
			ps.setInt(8, user.getElecCommuId());

			
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
			Date date = new java.sql.Date(3000-12-31);
			
			ps = connection.prepareStatement("Update app.SE_USER set EMAIL_TXT= ?, NAME = ?, PASSWORD_TXT=?, PASSWORD_EXPIRY_DT=?,ROLE_CD=?, ADDRESS_ID=?,ELEC_COMMU_ID=? where USER_ID = ?");
			ps.setString(1, user.getEmail());	
			ps.setString(2, user.getName());
			ps.setString(3, user.getPassword());
			ps.setDate(4,(java.sql.Date) date);
			ps.setString(5, user.getRole());
			ps.setInt(6, user.getUserId());
			ps.setInt(7, user.getAddressId());
			ps.setInt(8, user.getElecCommuId());
			ps.executeUpdate();
			ps.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	

}