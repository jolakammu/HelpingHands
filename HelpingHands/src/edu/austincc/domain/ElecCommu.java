package edu.austincc.domain;

public class ElecCommu {
	
	Integer elecCommuId;
	String elecCommuTyp;
	String elecCommuNum;

	public ElecCommu(Integer elecCommuId, String elecCommuTyp,
			String elecCommuNum) {
		super();
		this.elecCommuId = elecCommuId;
		this.elecCommuTyp = elecCommuTyp;
		this.elecCommuNum = elecCommuNum;
	}

	public Integer getElecCommuId() {
		return elecCommuId;
	}

	public void setElecCommuId(Integer elecCommuId) {
		this.elecCommuId = elecCommuId;
	}

	public String getElecCommuTyp() {
		return elecCommuTyp;
	}

	public void setElecCommuTyp(String elecCommuTyp) {
		this.elecCommuTyp = elecCommuTyp;
	}

	public String getElecCommuNum() {
		return elecCommuNum;
	}

	public void setElecCommuNum(String elecCommuNum) {
		this.elecCommuNum = elecCommuNum;
	}

	@Override
	public String toString() {
		return "ElecCommu [elecCommuId=" + elecCommuId + ", elecCommuTyp="
				+ elecCommuTyp + ", elecCommuNum=" + elecCommuNum + "]";
	}
	
	
	
}
