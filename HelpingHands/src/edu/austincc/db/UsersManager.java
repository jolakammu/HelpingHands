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

	public int addUser(User user) {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		ResultSet rs = null;
		int id = 0;
		try {
			Connection connection;
			connection = ds.getConnection();
			ps = connection.prepareStatement("select Max(ID) + 1 as ID from app.SE_USER");
			Date date = new java.sql.Date(3000-12-31);
			
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				id = Integer.parseInt(resultSet.getString("ID"));
			}
			ps = connection.prepareStatement("insert into app.SE_USER(ID,EMAIL_TXT,NAME,PASSWORD_TXT,PASSWORD_EXPIRY_DT,ROLE_CD) values (?,?,?,?,?,?)");
			ps.setInt(1, id);
			ps.setString(2, user.getEmail());	
			ps.setString(3, user.getName());
			ps.setString(4, user.getPassword());
			ps.setDate(5,(java.sql.Date) date);
			ps.setString(6, user.getRole());

			
			ps.executeUpdate();
			ps.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
    	return id;
	}	
	/*
	public ArrayList<User> getUsers() {
		ArrayList<User> users = new ArrayList<>();

		try {
			Connection connection;
			connection = ds.getConnection();

			PreparedStatement ps = connection.prepareStatement("select ID, UUID, NAME, PASSWORD, EMAIL from ACC_USER");
			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {
				users.add(new User( resultSet.getString("uuid"),
									resultSet.getString("name"),
									resultSet.getString("password"),
									resultSet.getString("email")));
			}

			resultSet.close();
			ps.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return users;
	}

	*/
/*
	public boolean addUser(User aUser)  {
		// TODO - Put the user in the user db
		// FIXME - This has a bug

		// Example with data from SQL Script
		// insert into users (name, password) values ('sam', 'abcd');
		boolean added = false;
		try {
			Connection connection;
			connection = ds.getConnection();

			String uname = aUser.getName();
			String upass = aUser.getPassword();
			String uemail = aUser.getEmail();
			String uUUID = aUser.getID().toString();
			Calendar rightNow = Calendar.getInstance();
			long id = rightNow.getTimeInMillis();
			
			PreparedStatement prepStatement = connection.prepareStatement("insert into ACC_USER (id, uuid, name, password, email) values (?, ?, ?, ?, ?)");

			prepStatement.setLong(1, id);
			prepStatement.setString(2, uUUID);
			prepStatement.setString(3, uname);
			prepStatement.setString(4, upass);
			prepStatement.setString(5, uemail);
			

			prepStatement.execute();


			prepStatement.close();
			connection.close();

			added = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return added;
	}
*/
	public User getUserForID(String email, String password) {
		
		User validateUser = null;

		try {
			Connection connection;
			connection = ds.getConnection();

			PreparedStatement ps = connection.prepareStatement("select ID,EMAIL_TXT,NAME,PASSWORD_TXT,PASSWORD_EXPIRY_DT,ROLE_CD from SE_USER where EMAIL_TXT = ? and PASSWORD_TXT = ?");
			ps.setString(1, email);
			ps.setString(2, password);
			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {
				//DateFormat df = new SimpleDateFormat("MM/DD/YYYY");
				//String dateString = resultSet.getString("PASSWORD_EXPIRY_DT");
				//Date passwordExpiryDate = df.parse(dateString);
				
				validateUser = new User( Integer.parseInt(resultSet.getString("ID")),
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

	
}