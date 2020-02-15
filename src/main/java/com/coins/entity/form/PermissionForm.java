package com.coins.entity.form;

import java.util.List;

import com.coins.entity.Permission;

public class PermissionForm extends Permission {

	private String pTitle;
	private String pName;
	private Integer page;
	private Integer size;
	private List<PermissionForm> items;
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

	public List<PermissionForm> getItems() {
		return items;
	}

	public void setItems(List<PermissionForm> items) {
		this.items = items;
	}

	public String getpTitle() {
		return pTitle;
	}

	public void setpTitle(String pTitle) {
		this.pTitle = pTitle;
	}

}
