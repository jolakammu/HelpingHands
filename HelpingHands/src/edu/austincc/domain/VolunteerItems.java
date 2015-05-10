package edu.austincc.domain;

import java.util.Date;

public class VolunteerItems {

	int volunteeringItemId; 
	String OrgName;
	String OrgCategory; 
	String WorkDesc;
	int ManHrs;
	Date WorkBeginDtTime;
	
	
	//Constructors
	public VolunteerItems(int volunteeringItemId, String orgName,
			String orgCategory, String workDesc, int manHrs,
			Date workBeginDtTime) {
		super();
		this.volunteeringItemId = volunteeringItemId;
		OrgName = orgName;
		OrgCategory = orgCategory;
		WorkDesc = workDesc;
		ManHrs = manHrs;
		WorkBeginDtTime = workBeginDtTime;
	}


	public int getVolunteeringItemId() {
		return volunteeringItemId;
	}


	public void setVolunteeringItemId(int volunteeringItemId) {
		this.volunteeringItemId = volunteeringItemId;
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


	@Override
	public String toString() {
		return "VolunteerItems [volunteeringItemId=" + volunteeringItemId
				+ ", OrgName=" + OrgName + ", OrgCategory=" + OrgCategory
				+ ", WorkDesc=" + WorkDesc + ", ManHrs=" + ManHrs
				+ ", WorkBeginDtTime=" + WorkBeginDtTime + "]";
	}  
	
	//To Sting Method
	
}
