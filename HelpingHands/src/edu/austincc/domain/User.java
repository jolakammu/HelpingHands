package edu.austincc.domain;

import java.util.Date;

public class User {	
	Integer	id;
	String	email;
	String	name;
	String	password;
	Date 	passwordExpiry;
	String	role;
	
	// Constructors
	public User(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public User(Integer id, String email, String name, String role) {
		super();
		this.id = id;
		this.email = email;
		this.name = name;
		this.role = role;
	}

	public User(Integer id, String email, String name, String password,
			Date passwordExpiry, String role) {
		super();
		this.id = id;
		this.email = email;
		this.name = name;
		this.password = password;
		this.passwordExpiry = passwordExpiry;
		this.role = role;
	}
	
	// toSting Method
	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", name=" + name
				+ ", password=" + password + ", passwordExpiry="
				+ passwordExpiry + ", role=" + role + "]";
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	
	
}
