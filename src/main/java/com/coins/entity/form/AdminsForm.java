package com.coins.entity.form;

import java.util.List;

import com.coins.entity.Admins;

public class AdminsForm extends Admins {
	private Integer page;
	private Integer size;
	private List<Integer> roleIds;
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
	public List<Integer> getRoleIds() {
		return roleIds;
	}
	public void setRoleIds(List<Integer> roleIds) {
		this.roleIds = roleIds;
	}
	
}
