package edu.austincc.domain;

public class VolunteerCSV {
	private String OrgName;
	private String OrgCategory; 
	private String WorkDesc;
	private String ManHrs;
	private String WorkBeginDtTime;
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
		OrgName = orgName;
		OrgCategory = orgCategory;
		WorkDesc = workDesc;
		ManHrs = manHrs;
		WorkBeginDtTime = workBeginDtTime;
		this.orgDelivery = orgDelivery;
		this.orgCity = orgCity;
		this.orgState = orgState;
		this.orgCountry = orgCountry;
		this.orgzip = orgzip;
		this.orgPhone = orgPhone;
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

	public String getManHrs() {
		return ManHrs;
	}

	public void setManHrs(String manHrs) {
		ManHrs = manHrs;
	}

	public String getWorkBeginDtTime() {
		return WorkBeginDtTime;
	}

	public void setWorkBeginDtTime(String workBeginDtTime) {
		WorkBeginDtTime = workBeginDtTime;
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
		return "VolunteerCSV [OrgName=" + OrgName + ", OrgCategory="
				+ OrgCategory + ", WorkDesc=" + WorkDesc + ", ManHrs=" + ManHrs
				+ ", WorkBeginDtTime=" + WorkBeginDtTime + ", orgDelivery="
				+ orgDelivery + ", orgCity=" + orgCity + ", orgState="
				+ orgState + ", orgCountry=" + orgCountry + ", orgzip="
				+ orgzip + ", orgPhone=" + orgPhone + "]";
	}

	
}
