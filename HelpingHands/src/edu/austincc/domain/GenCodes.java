package edu.austincc.domain;

public class GenCodes {

	private String genCd;
	private String name;

	public GenCodes(String genCd, String name) {
		super();
		this.genCd = genCd;
		this.name = name;
	}

	public String getGenCd() {
		return genCd;
	}

	public void setGenCd(String genCd) {
		this.genCd = genCd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "GenCodes [genCd=" + genCd + ", name=" + name + "]";
	}

}
