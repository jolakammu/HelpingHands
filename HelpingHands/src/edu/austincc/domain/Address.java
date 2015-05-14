package edu.austincc.domain;

public class Address {
	
	Integer addressId;
	String delivery;
	String city;
	String state;
	String country;
	String zip;
	
	public Address(Integer addressId, String delivery, String city, String state,
			String country, String zip) {
		super();
		this.addressId = addressId;
		this.delivery = delivery;
		this.city = city;
		this.state = state;
		this.country = country;
		this.zip = zip;
	}
	
	public int getAddressId() {
		return addressId;
	}
	@Override
	public String toString() {
		return "Address [addressId=" + addressId + ", delivery=" + delivery
				+ ", city=" + city + ", state=" + state + ", country="
				+ country + ", zip=" + zip + "]";
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
	public String getDelivery() {
		return delivery;
	}
	public void setDelivery(String delivery) {
		this.delivery = delivery;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}

	
}
