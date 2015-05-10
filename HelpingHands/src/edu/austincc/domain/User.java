package edu.austincc.domain;

import java.util.Date;

public class User {	
	Integer	userId;
	String	email;
	String	name;
	String	password;
	Date 	passwordExpiry;
	String	role;
	int		addressId;
	int     elecCommuId;
	
	
	//Constructors
	public User(String email) {
		super();
		this.email = email;
	}

	public User(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public User(Integer userId, String email, String name, String role) {
		super();
		this.userId = userId;
		this.email = email;
		this.name = name;
		this.role = role;
	}


	public User(Integer userId, String email, String name, String password,
			Date passwordExpiry, String role, int addressId, int elecCommuId) {
		super();
		this.userId = userId;
		this.email = email;
		this.name = name;
		this.password = password;
		this.passwordExpiry = passwordExpiry;
		this.role = role;
		this.addressId = addressId;
		this.elecCommuId = elecCommuId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getPasswordExpiry() {
		return passwordExpiry;
	}

	public void setPasswordExpiry(Date passwordExpiry) {
		this.passwordExpiry = passwordExpiry;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public int getElecCommuId() {
		return elecCommuId;
	}

	public void setElecCommuId(int elecCommuId) {
		this.elecCommuId = elecCommuId;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", email=" + email + ", name=" + name
				+ ", password=" + password + ", passwordExpiry="
				+ passwordExpiry + ", role=" + role + ", addressId="
				+ addressId + ", elecCommuId=" + elecCommuId + "]";
	}
	
	
	
	
}
