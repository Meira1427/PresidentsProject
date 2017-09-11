package com.meirapentermann.presidents;

public class President {
	private int termNum;
	private String firstName;
	private String middleName;
	private String lastName;
	private String termRange;
	private String imageLink;
	private String party;
	private String reason;
	
	public President(int termNum, String firstName, String middleName, 
			String lastName, String termRange,
			String imageLink, String party, String reason) {
		this.termNum = termNum;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.termRange = termRange;
		this.imageLink = imageLink;
		this.party = party;
		this.reason = reason;
	}

	public int getTermNum() {
		return termNum;
	}

	public void setTermNum(int termNum) {
		this.termNum = termNum;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getTermRange() {
		return termRange;
	}

	public void setTermRange(String termRange) {
		this.termRange = termRange;
	}

	public String getImageLink() {
		return imageLink;
	}

	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}

	public String getParty() {
		return party;
	}

	public void setParty(String party) {
		this.party = party;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}
