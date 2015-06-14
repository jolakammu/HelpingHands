package edu.austincc.domain;

public class VolunteerSchItem {
	
	private int volunteerSchItemId;
	private int signedManhrs;
	private User user;
	private VolunteerItems volunteerItems;
	
	public VolunteerSchItem(int volunteerSchItemId, int signedManhrs,
			User user, VolunteerItems volunteerItems) {
		super();
		this.volunteerSchItemId = volunteerSchItemId;
		this.signedManhrs = signedManhrs;
		this.user = user;
		this.volunteerItems = volunteerItems;
	}

	public int getVolunteerSchItemId() {
		return volunteerSchItemId;
	}

	public void setVolunteerSchItemId(int volunteerSchItemId) {
		this.volunteerSchItemId = volunteerSchItemId;
	}

	public int getSignedManhrs() {
		return signedManhrs;
	}

	public void setSignedManhrs(int signedManhrs) {
		this.signedManhrs = signedManhrs;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public VolunteerItems getVolunteerItems() {
		return volunteerItems;
	}

	public void setVolunteerItems(VolunteerItems volunteerItems) {
		this.volunteerItems = volunteerItems;
	}

	@Override
	public String toString() {
		return "VolunteerSchItem [volunteerSchItemId=" + volunteerSchItemId
				+ ", signedManhrs=" + signedManhrs + ", user=" + user
				+ ", volunteerItems=" + volunteerItems + "]";
	}
	
	
	
	
}
