package com.coins.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "sys_dict_type")
public class DictType implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	@Column(name = "NAME_EN")//英文名称
	private String NAME_EN;
	@Column(name = "NAME_CN")//中文名称
	private String NAME_CN;
	@Column(name = "STATUS")//状态
	private String STATUS;
	@Column(name = "SYSTEM_ID")//系统ID
	private String SYSTEM_ID;
	
	@Column(name = "CREATE_TIME")//创建时间
	private Date CREATE_TIME;
	@Column(name = "UPDATE_TIME")//修改时间
	private Date UPDATE_TIME;
	
	private ArrayList<Dict> dictList;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNAME_EN() {
		return NAME_EN;
	}

	public void setNAME_EN(String nAME_EN) {
		this.NAME_EN = nAME_EN;
	}

	public String getNAME_CN() {
		return NAME_CN;
	}

	public void setNAME_CN(String nAME_CN) {
		this.NAME_CN = nAME_CN;
	}

	public String getSTATUS() {
		return STATUS;
	}

	public void setSTATUS(String sTATUS) {
		STATUS = sTATUS;
	}

	public String getSYSTEM_ID() {
		return SYSTEM_ID;
	}

	public void setSYSTEM_ID(String sYSTEM_ID) {
		this.SYSTEM_ID = sYSTEM_ID;
	}

	public Date getCREATE_TIME() {
		return CREATE_TIME;
	}

	public void setCREATE_TIME(Date cREATE_TIME) {
		this.CREATE_TIME = cREATE_TIME;
	}

	public Date getUPDATE_TIME() {
		return UPDATE_TIME;
	}

	public void setUPDATE_TIME(Date uPDATE_TIME) {
		this.UPDATE_TIME = uPDATE_TIME;
	}

	public ArrayList<Dict> getDictList() {
		return dictList;
	}

	public void setDictList(ArrayList<Dict> dictList) {
		this.dictList = dictList;
	}
	
	
	
	
	
	
	

	

}
