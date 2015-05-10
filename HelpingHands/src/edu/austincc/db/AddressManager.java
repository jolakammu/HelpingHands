package edu.austincc.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import edu.austincc.domain.Address;

public class AddressManager {

	DataSource ds;

	public AddressManager(DataSource ds) {
		this.ds = ds;
	}

	public int addAddress(Address address) {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		ResultSet rs = null;
		int addressId = 0;
		try {
				Connection connection;
				connection = ds.getConnection();
				ps = connection.prepareStatement("select Max(ADDRESS_ID) + 1 as ADDRESS_ID from app.HH_ADDRESS");

			
				ResultSet resultSet = ps.executeQuery();
				while (resultSet.next()) {
					addressId = Integer.parseInt(resultSet.getString("ADDRESS_ID"));
				}
				ps = connection.prepareStatement("Insert into app.HH_ADDRESS(ADDRESS_ID, DELIVERY_TXT,CITY,STATE_CD,COUNTRY_CD,ZIP) values (?,?,?,?,?,?)");
				ps.setInt(1, addressId);
				ps.setString(2, address.getDelivery());	
				ps.setString(3, address.getCity());
				ps.setString(4, address.getState());
				ps.setString(5,address.getCountry());
				ps.setString(5,address.getZip());
			
			ps.executeUpdate();
			ps.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
    	return addressId;
	}	

	public void updateAddress(Address address) {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		try {
				Connection connection;
				connection = ds.getConnection();
				ps = connection.prepareStatement("Update app.HH_ADDRESS set DELIVERY_TXT=?, CITY=?, STATE_CD=?, COUNTRY_CD=?, ZIP_TXT=? where Address_id =?");
				ps.setString(1, address.getDelivery());	
				ps.setString(2, address.getCity());
				ps.setString(3, address.getState());
				ps.setString(4,address.getCountry());
				ps.setString(5,address.getZip());
				ps.setInt(6,address.getAddressId());
			
			ps.executeUpdate();
			ps.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}	
	

}
