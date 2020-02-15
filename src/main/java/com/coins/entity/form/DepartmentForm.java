package com.coins.entity.form;

import java.util.List;

import com.coins.entity.Department;

public class DepartmentForm extends Department {

	private String levelDesc;
	private String pName;
	private Integer page;
	private Integer size;
	private List<DepartmentForm> items;
	private boolean hasSub;

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public String getLevelDesc() {
		return levelDesc;
	}

	public void setLevelDesc(String levelDesc) {
		this.levelDesc = levelDesc;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public boolean isHasSub() {
		return hasSub;
	}

	public void setHasSub(boolean hasSub) {
		this.hasSub = hasSub;
	}

	public List<DepartmentForm> getItems() {
		return items;
	}

	public void setItems(List<DepartmentForm> items) {
		this.items = items;
	}
}
