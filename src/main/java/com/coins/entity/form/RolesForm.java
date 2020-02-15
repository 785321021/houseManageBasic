package com.coins.entity.form;

import java.util.List;

import com.coins.entity.Roles;

public class RolesForm extends Roles {
	private String stringStatus;
	private Integer page;
	private Integer size;
	private List<Integer> menu_id;

	public String getStringStatus() {
		return stringStatus;
	}

	public void setStringStatus(String stringStatus) {
		this.stringStatus = stringStatus;
	}

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

	public List<Integer> getMenu_id() {
		return menu_id;
	}

	public void setMenu_id(List<Integer> menu_id) {
		this.menu_id = menu_id;
	}

}
