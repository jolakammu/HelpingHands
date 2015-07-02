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

	public int getAddress(Address address) {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		ResultSet rs = null;
		Integer addressId = 0;
		try {
			Connection connection;
			connection = ds.getConnection();
			ps = connection
					.prepareStatement("select Coalesce(ADDRESS_ID,0) as ADDRESS_ID from app.HH_ADDRESS where DELIVERY_TXT = ? and CITY = ? and STATE_CD = ? and COUNTRY_CD = ?");
			ps.setString(1, address.getDelivery().toUpperCase());
			ps.setString(2, address.getCity().toUpperCase());
			ps.setString(3, address.getState().toUpperCase());
			ps.setString(4, address.getCountry().toUpperCase());

			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				addressId = resultSet.getInt("ADDRESS_ID");

			}
			ps.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return addressId;
	}

	public int addAddress(Address address) {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		ResultSet rs = null;
		Integer addressId = 0;
		try {
			Connection connection;
			connection = ds.getConnection();
			ps = connection
					.prepareStatement("select Coalesce(Max(ADDRESS_ID),0) + 1 as ADDRESS_ID from app.HH_ADDRESS");

			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				addressId = resultSet.getInt("ADDRESS_ID");

			}
			ps = connection
					.prepareStatement("Insert into app.HH_ADDRESS(ADDRESS_ID, DELIVERY_TXT,CITY,STATE_CD,COUNTRY_CD,ZIP_TXT) values (?,?,?,?,?,?)");
			ps.setInt(1, addressId);
			ps.setString(2, address.getDelivery().toUpperCase());
			ps.setString(3, address.getCity().toUpperCase());
			ps.setString(4, address.getState().toUpperCase());
			ps.setString(5, address.getCountry().toUpperCase());
			ps.setString(6, address.getZip());

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
			ps = connection
					.prepareStatement("Update app.HH_ADDRESS set DELIVERY_TXT=?, CITY=?, STATE_CD=?, COUNTRY_CD=?, ZIP_TXT=? where Address_id =?");
			ps.setString(1, address.getDelivery().toUpperCase());
			ps.setString(2, address.getCity().toUpperCase());
			ps.setString(3, address.getState().toUpperCase());
			ps.setString(4, address.getCountry().toUpperCase());
			ps.setString(5, address.getZip());
			ps.setInt(6, address.getAddressId());

			ps.executeUpdate();
			ps.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
