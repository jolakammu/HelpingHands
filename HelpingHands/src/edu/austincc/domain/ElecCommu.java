package edu.austincc.domain;

public class ElecCommu {
	
	int elecCommuId;
	String elecCommuTyp;
	int elecCommuNum;
	
	public ElecCommu(int elecCommuId, String elecCommuTyp, int elecCommuNum) {
		super();
		this.elecCommuId = elecCommuId;
		this.elecCommuTyp = elecCommuTyp;
		this.elecCommuNum = elecCommuNum;
	}
	
	public int getElecCommuId() {
		return elecCommuId;
	}
	public void setElecCommuId(int elecCommuId) {
		this.elecCommuId = elecCommuId;
	}
	public String getElecCommuTyp() {
		return elecCommuTyp;
	}
	public void setElecCommuTyp(String elecCommuTyp) {
		this.elecCommuTyp = elecCommuTyp;
	}
	public int getElecCommuNum() {
		return elecCommuNum;
	}
	public void setElecCommuNum(int elecCommuNum) {
		this.elecCommuNum = elecCommuNum;
	}
	
	
	@Override
	public String toString() {
		return "ElecCommu [elecCommuId=" + elecCommuId + ", elecCommuTyp="
				+ elecCommuTyp + ", elecCommuNum=" + elecCommuNum + "]";
	}

	
}
