package com.coins.entity.form;

public class MemberCard {

	private String corpCode;
	private String pointCode;
	private String memberCardCode;
	private String phone;
	private String memberName;
	private String pointName;
	private String memberDes;
	private String cardLevel;

	public String getMemberCardCode() {
		return memberCardCode;
	}

	public void setMemberCardCode(String memberCardCode) {
		this.memberCardCode = memberCardCode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

//    public String getCardKindID() {
//        return cardKindID;
//    }
//
//    public void setCardKindID(String cardKindID) {
//        this.cardKindID = cardKindID;
//    }
//
//    public String getCardKindName() {
//        return cardKindName;
//    }
//
//    public void setCardKindName(String cardKindName) {
//        this.cardKindName = cardKindName;
//    }

	public String getCorpCode() {
		return corpCode;
	}

	public void setCorpCode(String corpCode) {
		this.corpCode = corpCode;
	}

	public String getPointCode() {
		return pointCode;
	}

	public void setPointCode(String pointCode) {
		this.pointCode = pointCode;
	}

	public String getPointName() {
		return pointName;
	}

	public void setPointName(String pointName) {
		this.pointName = pointName;
	}

	public String getMemberDes() {
		return memberDes;
	}

	public void setMemberDes(String memberDes) {
		this.memberDes = memberDes;
	}

	public String getCardLevel() {
		return cardLevel;
	}

	public void setCardLevel(String cardLevel) {
		this.cardLevel = cardLevel;
	}

}
