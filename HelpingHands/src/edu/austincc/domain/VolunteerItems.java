package edu.austincc.domain;

import java.util.Date;

public class VolunteerItems {

	int volunteertemId; 
	String OrgName;
	String OrgCategory; 
	String WorkDesc;
	int ManHrs;
	Date WorkBeginDtTime;
	int  addressId;
	int  elecCommuId;
	
	
	public VolunteerItems(int volunteertemId, String orgName,
			String orgCategory, String workDesc, int manHrs,
			Date workBeginDtTime, int addressId, int elecCommuId) {
		super();
		this.volunteertemId = volunteertemId;
		OrgName = orgName;
		OrgCategory = orgCategory;
		WorkDesc = workDesc;
		ManHrs = manHrs;
		WorkBeginDtTime = workBeginDtTime;
		this.addressId = addressId;
		this.elecCommuId = elecCommuId;
	}


	public int getVolunteertemId() {
		return volunteertemId;
	}


	public void setVolunteertemId(int volunteertemId) {
		this.volunteertemId = volunteertemId;
	}


	public String getOrgName() {
		return OrgName;
	}


	public void setOrgName(String orgName) {
		OrgName = orgName;
	}


	public String getOrgCategory() {
		return OrgCategory;
	}


	public void setOrgCategory(String orgCategory) {
		OrgCategory = orgCategory;
	}


	public String getWorkDesc() {
		return WorkDesc;
	}


	public void setWorkDesc(String workDesc) {
		WorkDesc = workDesc;
	}


	public int getManHrs() {
		return ManHrs;
	}


	public void setManHrs(int manHrs) {
		ManHrs = manHrs;
	}


	public Date getWorkBeginDtTime() {
		return WorkBeginDtTime;
	}


	public void setWorkBeginDtTime(Date workBeginDtTime) {
		WorkBeginDtTime = workBeginDtTime;
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
		return "VolunteerItems [volunteertemId=" + volunteertemId
				+ ", OrgName=" + OrgName + ", OrgCategory=" + OrgCategory
				+ ", WorkDesc=" + WorkDesc + ", ManHrs=" + ManHrs
				+ ", WorkBeginDtTime=" + WorkBeginDtTime + ", addressId="
				+ addressId + ", elecCommuId=" + elecCommuId + "]";
	}
	
	
	
}
