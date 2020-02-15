package com.coins.entity.common;

import java.util.Date;


public class ModifyModel {

	public ModifyModel() {
	}

	public ModifyModel(Integer employeeId, Integer employeeId2, Integer employeeId3) {
	}

	public ModifyModel(Integer employeeId, String employeeName) {
		this.employeeId = employeeId;
		this.employeeName = employeeName;
	}

//	public ModifyModel(Employee employeeEntity) {
//		if (employeeEntity != null) {
//			this.employeeId = employeeEntity.getId();
//			this.employeeName = employeeEntity.getName();
//		}
//	}

	public Oper getOper() {
		return new Oper((Integer) getEmployeeId(), (String) getEmployeeName(), new Date());
	}

	private String mId = "mid";
	private String mName = "mname";
	private String mTime = "mtime";

	private String cId = "cid";
	private String cName = "cname";
	private String cTime = "ctime";
	private Object employeeId;
	private Object employeeName;

	public String getModifierId() {
		return modifierId;
	}

	public void setModifierId(String modifierId) {
		this.modifierId = modifierId;
	}

	public String getModifierName() {
		return modifierName;
	}

	public void setModifierName(String modifierName) {
		this.modifierName = modifierName;
	}

	public String getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(String modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	private String modifierId = "modifierId";
	private String modifierName = "modifierName";
	private String modifiedTime = "modifiedTime";

	private String creatorId = "creatorId";
	private String creatorName = "creatorName";
	private String createdTime = "createdTime";

	public Object getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Object employeeId) {
		this.employeeId = employeeId;
	}

	public Object getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(Object employeeName) {
		this.employeeName = employeeName;
	}

	public String getcTime() {
		return cTime;
	}

	public void setcTime(String cTime) {
		this.cTime = cTime;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public String getcId() {
		return cId;
	}

	public void setcId(String cId) {
		this.cId = cId;
	}

	public String getmTime() {
		return mTime;
	}

	public void setmTime(String mTime) {
		this.mTime = mTime;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}
}