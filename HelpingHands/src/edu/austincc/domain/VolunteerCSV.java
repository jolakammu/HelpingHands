package edu.austincc.domain;

public class VolunteerCSV {
	private String orgName;
	private String orgCategory;
	private String workDesc;
	private String manHrs;
	private String workBeginDtTime;
	private String orgDelivery;
	private String orgCity;
	private String orgState;
	private String orgCountry;
	private String orgzip;
	private String orgPhone;

	public VolunteerCSV(String orgName, String orgCategory, String workDesc,
			String manHrs, String workBeginDtTime, String orgDelivery,
			String orgCity, String orgState, String orgCountry, String orgzip,
			String orgPhone) {
		super();
		this.orgName = orgName;
		this.orgCategory = orgCategory;
		this.workDesc = workDesc;
		this.manHrs = manHrs;
		this.workBeginDtTime = workBeginDtTime;
		this.orgDelivery = orgDelivery;
		this.orgCity = orgCity;
		this.orgState = orgState;
		this.orgCountry = orgCountry;
		this.orgzip = orgzip;
		this.orgPhone = orgPhone;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		orgName = orgName;
	}

	public String getOrgCategory() {
		return orgCategory;
	}

	public void setOrgCategory(String orgCategory) {
		orgCategory = orgCategory;
	}

	public String getWorkDesc() {
		return workDesc;
	}

	public void setWorkDesc(String workDesc) {
		workDesc = workDesc;
	}

	public String getManHrs() {
		return manHrs;
	}

	public void setManHrs(String manHrs) {
		manHrs = manHrs;
	}

	public String getWorkBeginDtTime() {
		return workBeginDtTime;
	}

	public void setWorkBeginDtTime(String workBeginDtTime) {
		workBeginDtTime = workBeginDtTime;
	}

	public String getOrgDelivery() {
		return orgDelivery;
	}

	public void setOrgDelivery(String orgDelivery) {
		this.orgDelivery = orgDelivery;
	}

	public String getOrgCity() {
		return orgCity;
	}

	public void setOrgCity(String orgCity) {
		this.orgCity = orgCity;
	}

	public String getOrgState() {
		return orgState;
	}

	public void setOrgState(String orgState) {
		this.orgState = orgState;
	}

	public String getOrgCountry() {
		return orgCountry;
	}

	public void setOrgCountry(String orgCountry) {
		this.orgCountry = orgCountry;
	}

	public String getOrgzip() {
		return orgzip;
	}

	public void setOrgzip(String orgzip) {
		this.orgzip = orgzip;
	}

	public String getOrgPhone() {
		return orgPhone;
	}

	public void setOrgPhone(String orgPhone) {
		this.orgPhone = orgPhone;
	}

	@Override
	public String toString() {
		return "VolunteerCSV [OrgName=" + orgName + ", OrgCategory="
				+ orgCategory + ", WorkDesc=" + workDesc + ", ManHrs=" + manHrs
				+ ", WorkBeginDtTime=" + workBeginDtTime + ", orgDelivery="
				+ orgDelivery + ", orgCity=" + orgCity + ", orgState="
				+ orgState + ", orgCountry=" + orgCountry + ", orgzip="
				+ orgzip + ", orgPhone=" + orgPhone + "]";
	}

}
